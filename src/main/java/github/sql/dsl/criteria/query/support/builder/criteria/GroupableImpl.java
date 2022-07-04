package github.sql.dsl.criteria.query.support.builder.criteria;

import github.sql.dsl.criteria.query.builder.Groupable;
import github.sql.dsl.criteria.query.expression.SqlExpression;
import github.sql.dsl.criteria.query.expression.path.AttributePath;
import github.sql.dsl.criteria.query.expression.path.attribute.Attribute;
import github.sql.dsl.criteria.query.support.builder.component.ConstantArray;
import github.sql.dsl.util.Array;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupableImpl<T, NEXT> implements Groupable<T, NEXT> {

    private Array<SqlExpression<?>> values;
    private final Function<Array<SqlExpression<?>>, NEXT> mapper;

    public GroupableImpl(Array<SqlExpression<?>> values, Function<Array<SqlExpression<?>>, NEXT> mapper) {
        this.values = values == null ? new ConstantArray<>() : values;
        this.mapper = mapper;
    }

    @Override
    public NEXT groupBy(Attribute<T, ?> attribute) {
        AttributePath<T, ?> path = AttributePath.exchange(attribute);
        values = ConstantArray.from(values).concat(path);
        return mapper.apply(values);
    }

    @Override
    public NEXT groupBy(List<Attribute<T, ?>> attributes) {
        List<? extends AttributePath<T, ?>> list = attributes.stream().map(AttributePath::exchange)
                .collect(Collectors.toList());
        values = ConstantArray.from(values).concat(list);
        return mapper.apply(values);
    }

}
