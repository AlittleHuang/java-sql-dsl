package github.sql.dsl.query.api;

import github.sql.dsl.query.api.query.TypeQuery;
import github.sql.dsl.query.api.builder.*;
import github.sql.dsl.query.api.query.EntityQuery;
import github.sql.dsl.query.api.query.ObjectsQuery;
import github.sql.dsl.query.api.query.WhereBuilder;
import github.sql.dsl.query.api.query.Whereable;

public interface Query<T> extends
        Whereable<T, WhereBuilder<T>>,
        Fetchable<T, EntityQuery<T>>,
        Sortable<T, WhereBuilder<T>>,
        Groupable<T, ObjectsQuery<T>>,
        Selectable<T, ObjectsQuery<T>>,
        TypeQuery<T> {


}
