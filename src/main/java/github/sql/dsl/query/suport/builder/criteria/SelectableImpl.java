package github.sql.dsl.query.suport.builder.criteria;

import github.sql.dsl.query.api.builder.Selectable;
import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.path.BridgePath;
import github.sql.dsl.query.api.expression.path.bridge.AttributeBridge;
import github.sql.dsl.query.suport.builder.component.AggregateFunction;
import github.sql.dsl.query.suport.builder.component.ConstantList;
import github.sql.dsl.query.suport.builder.component.Selection;
import github.sql.dsl.query.suport.builder.component.SelectionImpl;
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
    public NEXT select(AttributeBridge<T, ?> selection) {
        BridgePath<T, ?> path = BridgePath.exchange(selection);
        Array<Selection<?>> list = values == null ? new ConstantList<>(path) : values.concat(path);
        return mapper.apply(list);
    }

    @Override
    public NEXT select(List<AttributeBridge<T, ?>> selections) {
        List<? extends BridgePath<T, ?>> paths = selections.stream()
                .map(BridgePath::exchange)
                .collect(Collectors.toList());
        Array<Selection<?>> list = values == null
                ? new ConstantList<>(paths)
                : values.concat(paths);
        return mapper.apply(list);
    }
}
