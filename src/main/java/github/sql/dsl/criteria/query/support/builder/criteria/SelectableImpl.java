package github.sql.dsl.criteria.query.support.builder.criteria;

import github.sql.dsl.criteria.query.builder.Selectable;
import github.sql.dsl.criteria.query.expression.SqlExpression;
import github.sql.dsl.criteria.query.expression.path.AttributePath;
import github.sql.dsl.criteria.query.expression.path.attribute.Attribute;
import github.sql.dsl.criteria.query.support.builder.component.ConstantArray;
import github.sql.dsl.util.Array;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SelectableImpl<T, NEXT> implements Selectable<T, NEXT> {

    private final Array<SqlExpression<?>> values;
    private final Function<Array<SqlExpression<?>>, NEXT> mapper;

    public SelectableImpl(Array<SqlExpression<?>> values,
                          Function<Array<SqlExpression<?>>, NEXT> mapper) {
        this.values = values;
        this.mapper = mapper;
    }

    @Override
    public NEXT select(Attribute<T, ?> selection) {
        AttributePath<T, ?> path = AttributePath.exchange(selection);
        Array<SqlExpression<?>> list = values == null ? new ConstantArray<>(path) : ConstantArray.from(values).concat(path);
        return mapper.apply(list);
    }

    @Override
    public NEXT select(List<Attribute<T, ?>> selections) {
        List<? extends AttributePath<T, ?>> paths = selections.stream()
                .map(AttributePath::exchange)
                .collect(Collectors.toList());
        Array<SqlExpression<?>> list = values == null
                ? new ConstantArray<>(paths)
                : ConstantArray.from(values).concat(paths);
        return mapper.apply(list);
    }
}
