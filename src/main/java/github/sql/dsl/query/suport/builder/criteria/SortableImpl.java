package github.sql.dsl.query.suport.builder.criteria;

import github.sql.dsl.query.api.builder.Sortable;
import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.path.BridgePath;
import github.sql.dsl.query.api.expression.path.bridge.AttributeBridge;
import github.sql.dsl.query.api.expression.path.bridge.ComparableAttributeBridge;
import github.sql.dsl.query.api.expression.path.bridge.NumberAttributeBridge;
import github.sql.dsl.query.api.expression.path.bridge.StringAttributeBridge;
import github.sql.dsl.query.suport.builder.component.ConstantList;
import github.sql.dsl.query.suport.builder.component.Order;
import github.sql.dsl.util.Array;

import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SortableImpl<T, NEXT> implements Sortable<T, NEXT> {

    private final Array<Order> values;
    private final Function<Array<Order>, NEXT> mapper;

    public SortableImpl(Array<Order> values,
                        Function<Array<Order>, NEXT> mapper) {
        this.values = values;
        this.mapper = mapper;
    }

    @Override
    public <U extends Number> Sort<NEXT> orderBy(NumberAttributeBridge<T, U> attribute) {
        return orderBy((AttributeBridge<?, ?>) attribute);
    }

    @Override
    public <U extends Date> Sort<NEXT> orderBy(ComparableAttributeBridge<T, U> attribute) {
        return orderBy((AttributeBridge<?, ?>) attribute);
    }

    @Override
    public Sort<NEXT> orderBy(StringAttributeBridge<T> attribute) {
        return orderBy((AttributeBridge<?, ?>) attribute);
    }

    public Sort<NEXT> orderBy(AttributeBridge<?, ?> attribute) {
        return new SortImpl<>(BridgePath.exchange(attribute), ((expression, desc) -> {
            Order order = new Order(expression, desc);
            Array<Order> orders = values == null ? new ConstantList<>(order) : values.concat(order);
            return mapper.apply(orders);
        }));
    }

    public static class SortImpl<T> implements Sort<T> {

        private final Expression<?> expression;
        private final BiFunction<Expression<?>, Boolean, T> mapper;

        public SortImpl(Expression<?> expression,
                        BiFunction<Expression<?>, Boolean, T> mapper) {
            this.expression = expression;
            this.mapper = mapper;
        }

        @Override
        public T asc() {
            return mapper.apply(expression, false);
        }

        @Override
        public T desc() {
            return mapper.apply(expression, true);
        }
    }

}
