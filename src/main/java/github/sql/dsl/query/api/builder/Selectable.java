package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.expression.path.bridge.AttributeBridge;

import java.util.List;

public interface Selectable<T, NEXT> {

    NEXT select(AttributeBridge<T, ?> selection);

    NEXT select(List<AttributeBridge<T, ?>> selections);

}
