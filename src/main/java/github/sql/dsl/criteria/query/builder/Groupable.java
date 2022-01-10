package github.sql.dsl.criteria.query.builder;

import github.sql.dsl.criteria.query.expression.path.attribute.Attribute;

import java.util.List;

public interface Groupable<T, NEXT> {

    NEXT groupBy(Attribute<T, ?> attribute);

    NEXT groupBy(List<Attribute<T, ?>> attributes);

}
