package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.expression.path.attribute.Attribute;

import java.util.List;

public interface Selectable<T, NEXT> {

    NEXT select(Attribute<T, ?> selection);

    NEXT select(List<Attribute<T, ?>> selections);

}
