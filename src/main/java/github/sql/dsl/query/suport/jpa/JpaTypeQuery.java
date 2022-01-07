package github.sql.dsl.query.suport.jpa;

import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.Operator;
import github.sql.dsl.query.api.expression.PathExpression;
import github.sql.dsl.query.api.query.ObjectsTypeQuery;
import github.sql.dsl.query.api.query.ProjectionResults;
import github.sql.dsl.query.api.query.TypeQuery;
import github.sql.dsl.query.suport.CriteriaQuery;
import github.sql.dsl.query.suport.builder.component.AggregateFunction;
import github.sql.dsl.query.suport.builder.component.Order;
import github.sql.dsl.query.suport.builder.component.Selection;
import github.sql.dsl.query.suport.jdbc.meta.EntityInformation;
import github.sql.dsl.util.Array;
import lombok.var;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

public class JpaTypeQuery<T> implements TypeQuery<T>, ObjectsTypeQuery {
    private final EntityManager entityManager;
    private final Class<T> entityType;
    private final CriteriaQuery criteria;


    public JpaTypeQuery(EntityManager entityManager, Class<T> type, CriteriaQuery criteria) {
        this.entityManager = entityManager;
        this.entityType = type;
        this.criteria = criteria;
    }

    @Override
    public List<Object[]> getObjectsList(int offset, int maxResult) {
        return new Builder<>(Object[].class).getObjectsList(offset, maxResult);
    }

    @Override
    public int count() {
        return new CountBuilder().count().intValue();
    }

    @Override
    public List<T> getResultList(int offset, int maxResul) {
        return new Builder<>(entityType).getResultList(offset, maxResul);
    }

    @Override
    public boolean exist(int offset) {
        return new Builder<>(Object.class).exist(offset);
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

    class Builder<R> {
        protected final Class<R> resultType;
        protected final CriteriaBuilder cb;
        protected final javax.persistence.criteria.CriteriaQuery<R> query;
        protected final Root<T> root;

        public Builder(Class<R> resultType) {
            this.resultType = resultType;
            cb = entityManager.getCriteriaBuilder();
            this.query = cb.createQuery(resultType);
            root = query.from(entityType);
        }

        public List<R> getResultList(int offset, int maxResult) {
            buildWhere();
            builderOrderBy();

            Array<PathExpression<?>> list = criteria.getFetchList();
            if (list != null) {
                for (PathExpression<?> expression : list) {
                    Fetch<?, ?> fetch = null;
                    PathExpression<?> path = expression.asPathExpression();
                    for (String stringPath : path) {
                        if (fetch == null) {
                            fetch = root.fetch(stringPath);
                        } else {
                            fetch = fetch.fetch(stringPath);
                        }
                    }
                }
            }

            TypedQuery<R> typedQuery = entityManager.createQuery(query);
            if (offset > 0) {
                typedQuery = typedQuery.setFirstResult(offset);
            }
            if (maxResult > 0) {
                typedQuery = typedQuery.setMaxResults(maxResult);
            }
            return typedQuery.getResultList();
        }

        public boolean exist(int offset) {
            buildWhere();

            EntityInformation<?> information = EntityInformation.getInstance(entityType);
            String fieldName = information.getIdAttribute().getFieldName();
            query.select(root.get(fieldName));
            TypedQuery<?> query = entityManager.createQuery(this.query);
            if (offset > 0) {
                query = query.setFirstResult(offset);
            }
            return !query.setMaxResults(1)
                    .getResultList()
                    .isEmpty();
        }

        public <U> ProjectionResults<T> projection(Class<U> projectionType) {
            throw new UnsupportedOperationException();
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
                            .map((Selection<?> selection) -> {
                                javax.persistence.criteria.Expression<?> e = toExpression(selection);
                                AggregateFunction function = selection.getAggregateFunction();
                                if (function != null) {
                                    switch (function) {
                                        case MIN:
                                            return cb.min(asNumber(e));
                                        case MAX:
                                            return cb.max(asNumber(e));
                                        case COUNT:
                                            return cb.count(e);
                                        case AVG:
                                            return cb.avg(asNumber(e));
                                        case SUM:
                                            return cb.sum(asNumber(e));
                                    }
                                }
                                return e;
                            })
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
            if (expression == null) {
                return cb.conjunction();
            }
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
                        return cb.gt(asNumber(e0), asNumber(list.get(1)));
                    case EQ:
                        return cb.equal(e0, list.get(1));
                    case DIFF:
                        return cb.notEqual(e0, list.get(1));
                    case GE:
                        return cb.ge(asNumber(e0), asNumber(list.get(1)));
                    case LT:
                        return cb.lt(asNumber(e0), asNumber(list.get(1)));
                    case LE:
                        return cb.le(asNumber(e0), asNumber(list.get(1)));
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
                    default:
                        throw new UnsupportedOperationException("unknown operator " + operator);
                }
            } else {
                throw new UnsupportedOperationException("unknown expression type " + expression.getClass());
            }
        }

        private javax.persistence.criteria.Expression<Number> asNumber(javax.persistence.criteria.Expression<?> e0) {
            Class<?> javaType = e0.getJavaType();
            if (javaType.isPrimitive() || Number.class.isAssignableFrom(javaType)) {
                //noinspection unchecked
                return (javax.persistence.criteria.Expression<Number>) e0;
            }
            return e0.as(Number.class);
        }

        private javax.persistence.criteria.Path<?> getPath(PathExpression<?> expression) {
            javax.persistence.criteria.Path<?> r = root;
            for (String s : expression) {
                r = r.get(s);
            }
            return r;
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

    public static void main(String[] args) {

        System.out.println(Number.class.isAssignableFrom(Integer.class));

        System.out.println(Integer.class.isAssignableFrom(Number.class));

        for (Operator operator : Operator.values()) {
            System.out.println("else if(\"" + operator.toString().toUpperCase() + "\".equals(operator.getSign())){\n\n}");
        }
    }

}
