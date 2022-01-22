package github.sql.dsl.criteria.query.support.builder.criteria;

import github.sql.dsl.criteria.query.builder.AggregateSelectable;
import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.path.AttributePath;
import github.sql.dsl.criteria.query.expression.path.attribute.Attribute;
import github.sql.dsl.criteria.query.support.builder.component.AggregateFunction;
import github.sql.dsl.criteria.query.support.builder.component.ConstantArray;
import github.sql.dsl.util.Array;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class AggregateSelectableImpl<T, NEXT> implements AggregateSelectable<T, NEXT> {

    private final Array<Expression<?>> values;
    private final Function<Array<Expression<?>>, NEXT> mapper;

    public AggregateSelectableImpl(Array<Expression<?>> values,
                                   Function<Array<Expression<?>>, NEXT> mapper) {
        this.values = values;
        this.mapper = mapper;
    }

    public NEXT select(Attribute<T, ?> attribute, @NotNull AggregateFunction function) {
        Expression<?> path = AttributePath.exchange(attribute);
        Expression<?> s = path.then(function.getOperator());
        Array<Expression<?>> list = values == null ? new ConstantArray<>(s) : ConstantArray.from(values).concat(s);
        return mapper.apply(list);
    }

}
