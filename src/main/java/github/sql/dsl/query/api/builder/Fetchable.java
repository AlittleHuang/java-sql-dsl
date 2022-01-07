package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.expression.path.bridge.EntityAttribute;

public interface Fetchable<T, NEXT> {

    NEXT fetch(EntityAttribute<T, ?> column);

}
