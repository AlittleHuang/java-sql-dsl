package github.sql.dsl.query.suport.builder.criteria;

import github.sql.dsl.query.api.builder.AggregateSelectable;
import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.path.AttributePath;
import github.sql.dsl.query.api.expression.path.bridge.Attribute;
import github.sql.dsl.query.suport.builder.component.AggregateFunction;
import github.sql.dsl.query.suport.builder.component.ConstantList;
import github.sql.dsl.query.suport.builder.component.Selection;
import github.sql.dsl.query.suport.builder.component.SelectionImpl;
import github.sql.dsl.util.Array;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class AggregateSelectableImpl<T, NEXT> implements AggregateSelectable<T, NEXT> {

    private final Array<Selection<?>> values;
    private final Function<Array<Selection<?>>, NEXT> mapper;

    public AggregateSelectableImpl(Array<Selection<?>> values,
                                   Function<Array<Selection<?>>, NEXT> mapper) {
        this.values = values;
        this.mapper = mapper;
    }

    public NEXT select(Attribute<T, ?> selection, @NotNull AggregateFunction function) {
        Expression<?> path = AttributePath.exchange(selection);
        SelectionImpl<?> s = new SelectionImpl<>(path, function);
        Array<Selection<?>> list = values == null ? new ConstantList<>(s) : values.concat(s);
        return mapper.apply(list);
    }

}
