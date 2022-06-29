package github.sql.dsl.internal.jpa;

import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.Operator;
import github.sql.dsl.criteria.query.expression.PathExpression;
import github.sql.dsl.criteria.query.support.CriteriaQuery;
import github.sql.dsl.criteria.query.support.builder.component.Order;
import github.sql.dsl.util.Array;
import lombok.var;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JpaResultQuery<T> {
    protected final EntityManager entityManager;
    protected final Class<T> entityType;
    protected final CriteriaQuery criteria;


    public JpaResultQuery(EntityManager entityManager, Class<T> type, CriteriaQuery criteria) {
        this.entityManager = entityManager;
        this.entityType = type;
        this.criteria = criteria;
    }


    public int count() {
        return new CountBuilder().count().intValue();
    }

    public boolean exist(int offset) {
        return new ExistBuilder(Object.class).exist(offset);
    }

    class CountBuilder extends Builder<Long> {

        public CountBuilder() {
            super(Long.class);
        }

        public Long count() {
            buildWhere();
            query.select(cb.count(root));
            TypedQuery<Long> typedQuery = entityManager.createQuery(query);
            return typedQuery.getSingleResult();
        }
    }

    public class ExistBuilder extends Builder<Object> {
        public ExistBuilder(Class<Object> resultType) {
            super(resultType);
        }

        public boolean exist(int offset) {
            buildWhere();
            query.select(cb.literal(true));
            TypedQuery<?> query = entityManager.createQuery(this.query);
            if (offset > 0) {
                query = query.setFirstResult(offset);
            }
            return !query.setMaxResults(1)
                    .getResultList()
                    .isEmpty();
        }
    }


    protected class Builder<R> {
        protected final Class<R> resultType;
        protected final CriteriaBuilder cb;
        protected final javax.persistence.criteria.CriteriaQuery<R> query;
        protected final Root<T> root;
        protected final Map<PathExpression<?>, FetchParent<?, ?>> fetched = new HashMap<>();

        public Builder(Class<R> resultType) {
            this.resultType = resultType;
            cb = entityManager.getCriteriaBuilder();
            this.query = cb.createQuery(resultType);
            root = query.from(entityType);
        }

        public List<R> getResultList(int offset, int maxResult) {
            Array<PathExpression<?>> list = criteria.getFetchList();
            if (list != null) {
                for (PathExpression<?> expression : list) {
                    Fetch<?, ?> fetch = null;
                    PathExpression<?> path = expression.asPathExpression();
                    for (int i = 0; i < path.size(); i++) {
                        Fetch<?, ?> cur = fetch;
                        String stringPath = path.get(i);
                        fetch = (Fetch<?, ?>) fetched.computeIfAbsent(path.offset(i + 1), k -> {
                            if (cur == null) {
                                return root.fetch(stringPath, JoinType.LEFT);
                            } else {
                                return cur.fetch(stringPath, JoinType.LEFT);
                            }
                        });

                    }
                }
            }

            buildWhere();
            builderOrderBy();

            TypedQuery<R> typedQuery = entityManager.createQuery(query);
            if (offset > 0) {
                typedQuery = typedQuery.setFirstResult(offset);
            }
            if (maxResult > 0) {
                typedQuery = typedQuery.setMaxResults(maxResult);
            }
            return typedQuery.getResultList();
        }

        public List<Object[]> getObjectsList(int offset, int maxResult) {
            buildWhere();
            Array<Expression<?>> groupBy = criteria.getGroupList();
            if (groupBy != null && !groupBy.isEmpty()) {
                query.groupBy(
                        groupBy.stream().map(this::toExpression).collect(Collectors.toList())
                );
            }
            builderOrderBy();
            javax.persistence.criteria.CriteriaQuery<R> select = query.multiselect(
                    criteria.getSelectionList().stream()
                            .map(this::toExpression)
                            .collect(Collectors.toList())
            );

            TypedQuery<?> typedQuery = entityManager.createQuery(select);

            if (offset > 0) {
                typedQuery = typedQuery.setFirstResult(offset);
            }
            if (maxResult > 0) {
                typedQuery = typedQuery.setMaxResults(maxResult);
            }
            return typedQuery.getResultList()
                    .stream()
                    .map(it -> {
                        if (it instanceof Object[]) {
                            return (Object[]) it;
                        }
                        return new Object[]{it};
                    })
                    .collect(Collectors.toList());
        }


        public Predicate toPredicate(Expression<?> expression) {
            javax.persistence.criteria.Expression<?> result = toExpression(expression);
            if (result instanceof Predicate) {
                return (Predicate) result;
            }
            return cb.isTrue(Operator.cast(toExpression(expression)));
        }

        public javax.persistence.criteria.Expression<?> toExpression(Expression<?> expression) {
            if (expression.getType() == Expression.Type.CONSTANT) {
                return cb.literal(expression.getValue());
            }
            if (expression.getType() == Expression.Type.PATH) {
                return getPath(expression.asPathExpression());
            }
            if (expression.getType() == Expression.Type.OPERATOR) {
                var list = expression.getExpressions()
                        .stream()
                        .map(this::toExpression)
                        .collect(Collectors.toList());
                Operator<?> operator = expression.getOperator();
                return operator.operate(cb, list);
            } else {
                throw new UnsupportedOperationException("unknown expression type " + expression.getClass());
            }
        }

        protected Path<?> getPath(PathExpression<?> expression) {
            From<?, ?> r = root;
            int size = expression.size();
            for (int i = 0; i < size; i++) {
                String s = expression.get(i);
                if (i != size - 1) {
                    r = join(expression.offset(i + 1));
                } else {
                    return r.get(s);
                }
            }

            return r;
        }

        private Join<?, ?> join(PathExpression<?> offset) {
            return (Join<?, ?>) fetched.compute(offset, (k, v) -> {
                if (v instanceof Join<?, ?>) {
                    return v;
                } else {
                    From<?, ?> r = root;
                    for (String s : offset) {
                        r = r.join(s, JoinType.LEFT);
                    }
                    return r;
                }
            });
        }

        protected void builderOrderBy() {
            Array<Order> orderList = criteria.getOrderList();
            if (orderList != null && !orderList.isEmpty()) {
                query.orderBy(
                        orderList.stream()
                                .map(o -> {
                                    if (o.isDesc()) {
                                        return cb.desc(toExpression(o.getExpression()));
                                    } else {
                                        return cb.asc(toExpression(o.getExpression()));
                                    }
                                })
                                .collect(Collectors.toList())
                );
            }
        }

        protected void buildWhere() {
            Expression<Boolean> where = criteria.getRestriction();
            if (where != null) {
                query.where(toPredicate(where));
            }
        }
    }

}
