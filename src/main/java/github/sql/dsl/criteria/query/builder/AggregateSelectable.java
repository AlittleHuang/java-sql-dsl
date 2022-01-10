package github.sql.dsl.criteria.query.builder;

import github.sql.dsl.criteria.query.expression.path.attribute.Attribute;
import github.sql.dsl.criteria.query.support.builder.component.AggregateFunction;
import org.jetbrains.annotations.NotNull;

public interface AggregateSelectable<T, NEXT> {

    NEXT select(Attribute<T, ?> selection, @NotNull AggregateFunction function);

}
