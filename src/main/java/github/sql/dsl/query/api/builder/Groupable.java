package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.expression.path.attribute.Attribute;

import java.util.List;

public interface Groupable<T, NEXT> {

    NEXT groupBy(Attribute<T, ?> attribute);

    NEXT groupBy(List<Attribute<T, ?>> attributes);

}
