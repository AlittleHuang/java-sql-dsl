package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.expression.path.bridge.Attribute;
import github.sql.dsl.query.suport.builder.component.AggregateFunction;
import org.jetbrains.annotations.NotNull;

public interface AggregateSelectable<T, NEXT> {

    NEXT select(Attribute<T, ?> selection, @NotNull AggregateFunction function);

}
