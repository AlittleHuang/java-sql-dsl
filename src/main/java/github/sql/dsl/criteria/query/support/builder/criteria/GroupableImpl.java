package github.sql.dsl.criteria.query.support.builder.criteria;

import github.sql.dsl.criteria.query.builder.Groupable;
import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.path.AttributePath;
import github.sql.dsl.criteria.query.expression.path.attribute.Attribute;
import github.sql.dsl.criteria.query.support.builder.component.ConstantList;
import github.sql.dsl.util.Array;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupableImpl<T, NEXT> implements Groupable<T, NEXT> {

    private Array<Expression<?>> values;
    private final Function<Array<Expression<?>>, NEXT> mapper;

    public GroupableImpl(Array<Expression<?>> values, Function<Array<Expression<?>>, NEXT> mapper) {
        this.values = values == null ? new ConstantList<>() : values;
        this.mapper = mapper;
    }

    @Override
    public NEXT groupBy(Attribute<T, ?> attribute) {
        AttributePath<T, ?> path = AttributePath.exchange(attribute);
        values = values.concat(path);
        return mapper.apply(values);
    }

    @Override
    public NEXT groupBy(List<Attribute<T, ?>> attributes) {
        List<? extends AttributePath<T, ?>> list = attributes.stream().map(AttributePath::exchange)
                .collect(Collectors.toList());
        values = values.concat(list);
        return mapper.apply(values);
    }

}
