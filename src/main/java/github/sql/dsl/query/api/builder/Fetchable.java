package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.expression.path.bridge.EntityAttributeBridge;

public interface Fetchable<T, NEXT> {

    NEXT fetch(EntityAttributeBridge<T, ?> column);

}
