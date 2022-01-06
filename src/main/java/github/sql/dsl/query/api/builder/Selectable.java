package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.expression.path.bridge.AttributeBridge;
import github.sql.dsl.query.suport.builder.component.AggregateFunction;

import java.util.List;

public interface Selectable<T, NEXT> {

    NEXT select(AttributeBridge<T, ?> selection);

    NEXT select(List<AttributeBridge<T, ?>> selections);

}
