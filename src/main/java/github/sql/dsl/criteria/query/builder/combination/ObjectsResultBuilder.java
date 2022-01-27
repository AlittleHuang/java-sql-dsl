package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.builder.ResultBuilder;
import github.sql.dsl.criteria.query.builder.Selectable;
import github.sql.dsl.criteria.query.builder.Sortable;

public interface ObjectsResultBuilder<T> extends
        Whereable<T, ObjectsResultBuilder<T>>,
        Sortable<T, ObjectsResultBuilder<T>>,
        Selectable<T, ObjectsResultBuilder<T>>,
        ResultBuilder<Object[]> {


}
