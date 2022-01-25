package github.sql.dsl.criteria.query.support.builder.criteria;

import github.sql.dsl.criteria.query.builder.Sortable;
import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.path.AttributePath;
import github.sql.dsl.criteria.query.expression.path.attribute.Attribute;
import github.sql.dsl.criteria.query.expression.path.attribute.ComparableAttribute;
import github.sql.dsl.criteria.query.expression.path.attribute.NumberAttribute;
import github.sql.dsl.criteria.query.expression.path.attribute.StringAttribute;
import github.sql.dsl.criteria.query.support.builder.component.ConstantArray;
import github.sql.dsl.criteria.query.support.builder.component.Order;
import github.sql.dsl.util.Array;

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
    public <U extends Number & Comparable<?>> Sort<NEXT> orderBy(NumberAttribute<T, U> attribute) {
        return orderBy((Attribute<?, ?>) attribute);
    }

    @Override
    public <U extends Comparable<?>> Sort<NEXT> orderBy(ComparableAttribute<T, U> attribute) {
        return orderBy((Attribute<?, ?>) attribute);
    }

    @Override
    public Sort<NEXT> orderBy(StringAttribute<T> attribute) {
        return orderBy((Attribute<?, ?>) attribute);
    }

    public Sort<NEXT> orderBy(Attribute<?, ?> attribute) {
        return new SortImpl<>(AttributePath.exchange(attribute), ((expression, desc) -> {
            Order order = new Order(expression, desc);
            Array<Order> orders = values == null ? new ConstantArray<>(order) : ConstantArray.from(values).concat(order);
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
