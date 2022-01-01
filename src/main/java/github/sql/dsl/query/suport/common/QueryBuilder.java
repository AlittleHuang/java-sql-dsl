package github.sql.dsl.query.suport.common;

import github.sql.dsl.query.api.*;
import github.sql.dsl.query.suport.common.model.*;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@ToString
public class QueryBuilder<T> extends OperatorExpressionBuilder<T, Predicate<T>>
        implements github.sql.dsl.query.api.QueryBuilder<T>, FetchBuilder<T>, Predicate<T>,
        ObjectsTypeQueryBuilder<T>, SelectableQueryBuilder<T>, CriteriaQuery {

    private final Class<T> type;

    private final ResultsFactory resultsFactory;

    private final List<Order> orderList;

    private final List<Expression<?>> groupList;

    private final List<Expression<?>> selection;

    private final List<PathExpression<?>> fetch;


    public QueryBuilder(Class<T> type, ResultsFactory resultsFactory) {
        super(null);
        this.type = type;
        this.resultsFactory = resultsFactory;
        orderList = null;
        groupList = null;
        selection = null;
        fetch = null;
    }

    public QueryBuilder(Class<T> type,
                        ResultsFactory resultsFactory,
                        Expression<Boolean> where,
                        List<Order> orderList,
                        List<Expression<?>> groupList,
                        List<Expression<?>> selection,
                        List<PathExpression<?>> fetch) {
        super(where);
        this.type = type;
        this.resultsFactory = resultsFactory;
        this.orderList = orderList;
        this.groupList = groupList;
        this.selection = selection;
        this.fetch = fetch;
    }

    @Override
    public <U extends Number> Sortable<TypeQueryBuilder<T>> orderBy(NumberAttributeBridge<T, U> column) {
        return addOrderBy(column);
    }

    @Override
    public <U extends Date> Sortable<TypeQueryBuilder<T>> orderBy(ComparableAttributeBridge<T, U> column) {
        return addOrderBy(column);
    }

    @Override
    public Sortable<TypeQueryBuilder<T>> orderBy(StringAttributeBridge<T> column) {
        return addOrderBy(column);
    }

    @Override
    public ObjectsTypeQueryBuilder<T> groupBy(AttributeBridge<T, ?> selections) {
        return groupBy(Collections.singletonList(selections));
    }

    @Override
    public ObjectsTypeQueryBuilder<T> groupBy(List<AttributeBridge<T, ?>> attributeBridges) {
        List<Expression<?>> models = attributeBridges.stream()
                .map(BridgePath::asExpression)
                .collect(Collectors.toList());
        return addGroupBy(models);
    }

    @Override
    public SelectableQueryBuilder<T> select(AttributeBridge<T, ?> selection) {
        return select(Collections.singletonList(selection));
    }

    @Override
    public SelectableQueryBuilder<T> select(List<AttributeBridge<T, ?>> attributeBridges) {
        List<Expression<?>> models = attributeBridges.stream().map(BridgePath::asExpression)
                .collect(Collectors.toList());
        return addSelect(models);
    }

    @Override
    public FetchBuilder<T> fetch(EntityAttributeBridge<T, ?> column) {
        List<PathExpression<?>> fetch = Stream
                .concat(getFetch().stream(), Stream.of(BridgePath.exchange(column)))
                .collect(Collectors.toList());
        return exchangeFetch(fetch);
    }

    @Override
    public int count() {
        return getResults().count();
    }

    @Override
    public List<T> getResultList(int offset, int maxResultant) {
        return getResults().getResultList(offset, maxResultant);
    }

    @Override
    public boolean exist(int offset) {
        return getResults().exist(offset);
    }

    @Override
    public <U> ProjectionResults<T> projection(Class<U> projectionType) {
        return getResults().projection(projectionType);
    }

    @NotNull
    private Sortable<TypeQueryBuilder<T>> addOrderBy(AttributeBridge<?, ?> attributeBridge) {
        return new Sortable<TypeQueryBuilder<T>>() {
            @Override
            public TypeQueryBuilder<T> asc() {
                boolean desc = false;
                return order(desc);
            }

            @NotNull
            private QueryBuilder<T> order(boolean desc) {
                Order order = new Order(BridgePath.exchange(attributeBridge), desc);
                List<Order> orderBy;
                if (getOrderList() != null) {
                    orderBy = new ArrayList<>(getOrderList().size() + 1);
                    orderBy.addAll(getOrderList());
                    orderBy.add(new Order(order.getExpression(), desc));
                } else {
                    orderBy = Collections.singletonList(order);
                }
                return exchangeOrderBy(orderBy);
            }

            @Override
            public TypeQueryBuilder<T> desc() {
                return order(true);
            }

        };
    }


    private TypeQuery<T> getResults() {
        return resultsFactory.results(this, type);
    }

    private ObjectsTypeQuery getArrayResults() {
        return resultsFactory.arrayResults(this, type);
    }

    @Override
    public List<Object[]> getObjectsList(int offset, int maxResult) {
        return getArrayResults().getObjectsList(offset, maxResult);
    }

    public static <T> List<T> merger(List<? extends T> a, List<? extends T> b) {
        if (a == null && b == null) {
            return null;
        }
        if (a == null) {
            return new ArrayList<>(b);
        }
        if (b == null) {
            return new ArrayList<>(a);
        }
        return Stream.of(a, b)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public QueryBuilder<T> addGroupBy(List<? extends Expression<?>> expressions) {
        List<Expression<?>> groupBy = merger(getGroupList(), expressions);
        return exchangeGroupBy(groupBy);
    }

    public QueryBuilder<T> addSelect(List<? extends Expression<?>> expressions) {
        List<Expression<?>> select = merger(getSelection(), expressions);
        return exchangeSelect(select);
    }

    @Override
    public <U extends Entity> PathBuilder<T, U, Predicate<T>> where(EntityAttributeBridge<T, U> column) {
        return and(column);
    }

    @Override
    public <U> ExpressionBuilder<T, U, Predicate<T>> where(AttributeBridge<T, U> attributeBridge) {
        return and(attributeBridge);
    }

    @Override
    public <U extends Number> NumberExpressionBuilder<T, U, Predicate<T>> where(NumberAttributeBridge<T, U> column) {
        return and(column);
    }

    @Override
    public <U extends Date> ComparableExpressionBuilder<T, U, Predicate<T>> where(ComparableAttributeBridge<T, U> column) {
        return and(column);
    }

    @Override
    public StringExpressionBuilder<T, Predicate<T>> where(StringAttributeBridge<T> column) {
        return and(column);
    }

    @Override
    public <U extends Entity> PathBuilder<T, U, Predicate<T>> whereNot(EntityAttributeBridge<T, U> column) {
        return andNot(column);
    }

    @Override
    public <U> ExpressionBuilder<T, U, Predicate<T>> whereNot(AttributeBridge<T, U> attributeBridge) {
        return andNot(attributeBridge);
    }

    @Override
    public <U extends Number> NumberExpressionBuilder<T, U, Predicate<T>> whereNot(NumberAttributeBridge<T, U> column) {
        return andNot(column);
    }

    @Override
    public <U extends Date> ComparableExpressionBuilder<T, U, Predicate<T>> whereNot(ComparableAttributeBridge<T, U> column) {
        return andNot(column);
    }

    @Override
    public StringExpressionBuilder<T, Predicate<T>> whereNot(StringAttributeBridge<T> column) {
        return andNot(column);
    }

    @Override
    public Predicate<T> wheres(Function<Builder<T>, BooleanExpression> column) {
        return andCombined(column);
    }

    class WhereBuilder extends OperatorExpressionBuilder<T, Builder<T>> implements Builder<T> {

        public WhereBuilder(OperatorExpression<Boolean> where) {
            super(where);
        }

        @Override
        protected WhereBuilder exchangeWhere(OperatorExpression<Boolean> where) {
            return new WhereBuilder(where);
        }


        @Override
        public BooleanExpression build() {
            Expression<Boolean> where = getRestriction();
            switch (where.getType()) {
                case PATH:
                    return BooleanPathExpression.fromPathExpression(where);
                case CONSTANT:
                    return BooleanConstantExpression.fromConstantExpression(where);
                case OPERATOR:
                    return new BooleanOperatorExpression(where.getExpressions(), where.getOperator());
                default:
                    throw new UnsupportedOperationException("unknown expression type " + where.getClass());
            }
        }
    }

    @Override
    public Predicate<T> andCombined(Function<Builder<T>, BooleanExpression> builder) {
        BooleanExpression expression = builder.apply(new WhereBuilder(null));
        if (expression == null) {
            return this;
        }
        if (getRestriction() == null && expression instanceof BooleanOperatorExpression) {
            return exchangeWhere((BooleanOperatorExpression) expression);
        }
        OperatorExpression<Boolean> where = getRestriction().then(Operator.AND, expression);
        return exchangeWhere(where);
    }

    @Override
    public Predicate<T> orCombined(Function<Builder<T>, BooleanExpression> builder) {
        BooleanExpression expression = builder.apply(new WhereBuilder(null));
        if (expression == null) {
            return this;
        }
        OperatorExpression<Boolean> where = getRestriction().then(Operator.OR, expression);
        return exchangeWhere(where);
    }

    public QueryBuilder<T> exchangeWhere(OperatorExpression<Boolean> where) {
        return new QueryBuilder<>(type, resultsFactory, where, orderList, groupList, selection, fetch);
    }

    public QueryBuilder<T> exchangeOrderBy(List<Order> order) {
        return new QueryBuilder<>(type, resultsFactory, restriction, order, groupList, selection, fetch);
    }

    public QueryBuilder<T> exchangeGroupBy(List<Expression<?>> groupBy) {
        return new QueryBuilder<>(type, resultsFactory, restriction, orderList, groupBy, selection, fetch);
    }

    public QueryBuilder<T> exchangeSelect(List<Expression<?>> select) {
        return new QueryBuilder<>(type, resultsFactory, restriction, orderList, groupList, select, fetch);
    }

    public QueryBuilder<T> exchangeFetch(List<PathExpression<?>> fetch) {
        return new QueryBuilder<>(type, resultsFactory, restriction, orderList, groupList, selection, fetch);
    }

}
