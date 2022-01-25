package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.builder.Selectable;
import github.sql.dsl.criteria.query.builder.Sortable;
import github.sql.dsl.criteria.query.builder.TypeResultQuery;

public interface ArrayQuery<T> extends
        Whereable<T, ArrayQuery<T>>,
        Sortable<T, ArrayQuery<T>>,
        Selectable<T, ArrayQuery<T>>,
        TypeResultQuery<Object[]> {


}
