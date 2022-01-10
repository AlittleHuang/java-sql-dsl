package github.sql.dsl.criteria.query.support.builder.criteria;

import github.sql.dsl.criteria.query.builder.Selectable;
import github.sql.dsl.criteria.query.expression.path.AttributePath;
import github.sql.dsl.criteria.query.expression.path.attribute.Attribute;
import github.sql.dsl.criteria.query.support.builder.component.ConstantList;
import github.sql.dsl.criteria.query.support.builder.component.Selection;
import github.sql.dsl.util.Array;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SelectableImpl<T, NEXT> implements Selectable<T, NEXT> {

    private final Array<Selection<?>> values;
    private final Function<Array<Selection<?>>, NEXT> mapper;

    public SelectableImpl(Array<Selection<?>> values,
                          Function<Array<Selection<?>>, NEXT> mapper) {
        this.values = values;
        this.mapper = mapper;
    }

    @Override
    public NEXT select(Attribute<T, ?> selection) {
        AttributePath<T, ?> path = AttributePath.exchange(selection);
        Array<Selection<?>> list = values == null ? new ConstantList<>(path) : values.concat(path);
        return mapper.apply(list);
    }

    @Override
    public NEXT select(List<Attribute<T, ?>> selections) {
        List<? extends AttributePath<T, ?>> paths = selections.stream()
                .map(AttributePath::exchange)
                .collect(Collectors.toList());
        Array<Selection<?>> list = values == null
                ? new ConstantList<>(paths)
                : values.concat(paths);
        return mapper.apply(list);
    }
}
