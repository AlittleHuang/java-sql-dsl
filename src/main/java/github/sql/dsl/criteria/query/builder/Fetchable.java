package github.sql.dsl.criteria.query.builder;

import github.sql.dsl.criteria.query.expression.path.attribute.EntityAttribute;

public interface Fetchable<T, NEXT> {

    NEXT fetch(EntityAttribute<T, ?> column);

}
