package github.sql.dsl.query.api.query;

import github.sql.dsl.query.api.builder.*;

public interface WhereBuilder<T> extends
        PredicateCombinable<T, WhereBuilder<T>>,
        Fetchable<T, EntityQuery<T>>,
        Sortable<T, WhereBuilder<T>>,
        Groupable<T, ObjectsQuery<T>>,
        Selectable<T, ObjectsQuery<T>>,
        TypeQuery<T> {


}
