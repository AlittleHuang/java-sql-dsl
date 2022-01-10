package github.sql.dsl.criteria.query.builder;

import github.sql.dsl.criteria.query.expression.path.attribute.Attribute;

import java.util.List;

public interface Selectable<T, NEXT> {

    NEXT select(Attribute<T, ?> selection);

    NEXT select(List<Attribute<T, ?>> selections);

}
