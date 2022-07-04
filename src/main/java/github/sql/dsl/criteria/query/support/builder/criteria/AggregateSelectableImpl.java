package github.sql.dsl.criteria.query.support.builder.criteria;

import github.sql.dsl.criteria.query.builder.AggregateSelectable;
import github.sql.dsl.criteria.query.expression.SqlExpression;
import github.sql.dsl.criteria.query.expression.path.AttributePath;
import github.sql.dsl.criteria.query.expression.path.attribute.Attribute;
import github.sql.dsl.criteria.query.support.builder.component.AggregateFunction;
import github.sql.dsl.criteria.query.support.builder.component.ConstantArray;
import github.sql.dsl.util.Array;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class AggregateSelectableImpl<T, NEXT> implements AggregateSelectable<T, NEXT> {

    private final Array<SqlExpression<?>> values;
    private final Function<Array<SqlExpression<?>>, NEXT> mapper;

    public AggregateSelectableImpl(Array<SqlExpression<?>> values,
                                   Function<Array<SqlExpression<?>>, NEXT> mapper) {
        this.values = values;
        this.mapper = mapper;
    }

    public NEXT select(Attribute<T, ?> attribute, @NotNull AggregateFunction function) {
        SqlExpression<?> path = AttributePath.exchange(attribute);
        SqlExpression<?> s = path.then(function.getOperator());
        Array<SqlExpression<?>> list = values == null ? new ConstantArray<>(s) : ConstantArray.from(values).concat(s);
        return mapper.apply(list);
    }

}
