package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.expression.path.bridge.AttributeBridge;

import java.util.List;

public interface Groupable<T, NEXT> {

    NEXT groupBy(AttributeBridge<T, ?> attribute);

    NEXT groupBy(List<AttributeBridge<T, ?>> attributes);

}
