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
            return cb.isTrue(toExpression(expression).as(Boolean.class));
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
                javax.persistence.criteria.Expression<?> e0 = list.get(0);
                Operator operator = expression.getOperator();
                switch (operator) {
                    case NOT:
                        return cb.not(e0.as(Boolean.class));
                    case AND:
                        return cb.and(e0.as(Boolean.class), list.get(1).as(Boolean.class));
                    case OR:
                        return cb.or(e0.as(Boolean.class), list.get(1).as(Boolean.class));
                    case GT:
                        return cb.greaterThan(asComparable(e0), asComparable(list.get(1)));
                    case EQ:
                        return cb.equal(e0, list.get(1));
                    case NE:
                        return cb.notEqual(e0, list.get(1));
                    case GE:
                        return cb.greaterThanOrEqualTo(asComparable(e0), asComparable(list.get(1)));
                    case LT:
                        return cb.lessThan(asComparable(e0), asComparable(list.get(1)));
                    case LE:
                        return cb.lessThanOrEqualTo(asComparable(e0), asComparable(list.get(1)));
                    case LIKE:
                        return cb.like(e0.as(String.class), list.get(1).as(String.class));
                    case LOWER:
                        return cb.lower(e0.as(String.class));
                    case UPPER:
                        return cb.upper(e0.as(String.class));
                    case SUBSTRING:
                        if (list.size() == 2) {
                            return cb.substring(e0.as(String.class), list.get(1).as(Integer.class));
                        } else if (list.size() > 2) {
                            return cb.substring(e0.as(String.class),
                                    list.get(1).as(Integer.class), list.get(2).as(Integer.class));
                        } else {
                            throw new IllegalArgumentException("argument length error");
                        }
                    case TRIM:
                        return cb.trim(e0.as(String.class));
                    case LENGTH:
                        return cb.length(e0.as(String.class));
                    case ADD:
                        return cb.sum(asNumber(e0), asNumber(list.get(1)));
                    case SUBTRACT:
                        return cb.diff(asNumber(e0), asNumber(list.get(1)));
                    case MULTIPLY:
                        return cb.prod(asNumber(e0), asNumber(list.get(1)));
                    case DIVIDE:
                        return cb.quot(asNumber(e0), asNumber(list.get(1)));
                    case MOD:
                        return cb.mod(e0.as(Integer.class), list.get(1).as(Integer.class));
                    case NULLIF:
                        return cb.nullif(e0, list.get(1));
                    case IF_NULL:
                        return cb.coalesce(e0, list.get(1));
                    case ISNULL:
                        return cb.isNull(e0);
                    case IN:
                        if (list.size() == 1) {
                            return cb.literal(false);
                        }
                        CriteriaBuilder.In<Object> in = cb.in(e0);
                        for (int i = 1; i < list.size(); i++) {
                            in = in.value(list.get(i));
                        }
                        return in;
                    case BETWEEN:
                        //noinspection unchecked
                        return cb.between(
                                (javax.persistence.criteria.Expression<? extends Comparable<Object>>) e0,
                                (javax.persistence.criteria.Expression<? extends Comparable<Object>>) list.get(1),
                                (javax.persistence.criteria.Expression<? extends Comparable<Object>>) list.get(2)
                        );
                    case MIN:
                        return cb.min(asNumber(e0));
                    case MAX:
                        return cb.max(asNumber(e0));
                    case COUNT:
                        return cb.count(e0);
                    case AVG:
                        return cb.avg(asNumber(e0));
                    case SUM:
                        return cb.sum(asNumber(e0));
                    default:
                        throw new UnsupportedOperationException("unknown operator " + operator);
                }
            } else {
                throw new UnsupportedOperationException("unknown expression type " + expression.getClass());
            }
        }

        protected javax.persistence.criteria.Expression<Number> asNumber(javax.persistence.criteria.Expression<?> e0) {
            //noinspection unchecked
            return (javax.persistence.criteria.Expression<Number>) e0;
        }

        protected javax.persistence.criteria.Expression<? extends Comparable<Object>>
        asComparable(javax.persistence.criteria.Expression<?> e0) {
            //noinspection unchecked
            return (javax.persistence.criteria.Expression<? extends Comparable<Object>>) e0;
        }

        protected Path<?> getPath(PathExpression<?> expression) {
            Path<?> r = root;
            int iMax = expression.size() - 1;
            for (int i = 0; i < expression.size(); i++) {
                String s = expression.get(i);
                if (i != iMax) {
                    join(expression.offset(i + 1));
                }
                r = r.get(s);
            }

            return r;
        }

        private void join(PathExpression<?> offset) {
            fetched.computeIfAbsent(offset, k -> {
                From<?, ?> r = root;
                for (String s : offset) {
                    r = r.join(s, JoinType.LEFT);
                }
                return r;
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
