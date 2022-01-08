package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.expression.path.attribute.EntityAttribute;

public interface Fetchable<T, NEXT> {

    NEXT fetch(EntityAttribute<T, ?> column);

}
