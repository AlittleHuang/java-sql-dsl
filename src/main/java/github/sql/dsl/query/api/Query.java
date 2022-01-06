package github.sql.dsl.query.api;

import github.sql.dsl.query.api.builder.*;
import github.sql.dsl.query.api.query.*;

public interface Query<T> extends
        Whereable<T, WhereBuilder<T>>,
        Fetchable<T, EntityQuery<T>>,
        Sortable<T, WhereBuilder<T>>,
        Groupable<T, ObjectsQuery<T>>,
        Selectable<T, ObjectsQuery<T>>,
        AggregateSelectable<T, AggregateObjectsQuery<T>>,
        TypeQuery<T> {


}
