package github.sql.dsl.query.suport.builder.criteria;

import github.sql.dsl.util.Array;
import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.builder.Selectable;
import github.sql.dsl.query.api.expression.path.bridge.AttributeBridge;
import github.sql.dsl.query.api.expression.path.BridgePath;
import github.sql.dsl.query.suport.builder.component.ConstantList;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SelectableImpl<T, NEXT> implements Selectable<T, NEXT> {

    private final Array<Expression<?>> values;
    private final Function<Array<Expression<?>>, NEXT> mapper;

    public SelectableImpl(Array<Expression<?>> values,
                          Function<Array<Expression<?>>, NEXT> mapper) {
        this.values = values;
        this.mapper = mapper;
    }

    @Override
    public NEXT select(AttributeBridge<T, ?> selection) {
        BridgePath<T, ?> path = BridgePath.exchange(selection);
        Array<Expression<?>> list = values == null ? new ConstantList<>(path) : values.concat(path);
        return mapper.apply(list);
    }

    @Override
    public NEXT select(List<AttributeBridge<T, ?>> selections) {
        List<? extends BridgePath<T, ?>> paths = selections.stream()
                .map(BridgePath::exchange)
                .collect(Collectors.toList());
        Array<Expression<?>> list = values == null
                ? new ConstantList<>(paths)
                : values.concat(paths);
        return mapper.apply(list);
    }
}
