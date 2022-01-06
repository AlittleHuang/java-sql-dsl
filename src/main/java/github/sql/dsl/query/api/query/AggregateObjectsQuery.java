package github.sql.dsl.query.api.query;

import github.sql.dsl.query.api.builder.AggregateSelectable;
import github.sql.dsl.query.api.builder.Groupable;
import github.sql.dsl.query.api.builder.Sortable;

public interface AggregateObjectsQuery<T> extends
        Whereable<T, ObjectsQuery<T>>,
        Sortable<T, ObjectsQuery<T>>,
        Groupable<T, ObjectsQuery<T>>,
        AggregateSelectable<T, AggregateObjectsQuery<T>>,
        ObjectsTypeQuery {


}
