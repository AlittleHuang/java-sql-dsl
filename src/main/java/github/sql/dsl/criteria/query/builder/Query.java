package github.sql.dsl.criteria.query.builder;

import github.sql.dsl.criteria.query.builder.combination.*;

public interface Query<T> extends
        Whereable<T, WhereBuilder<T>>,
        Fetchable<T, EntityQuery<T>>,
        Sortable<T, WhereBuilder<T>>,
        Groupable<T, ArrayQuery<T>>,
        Selectable<T, ArrayQuery<T>>,
        AggregateSelectable<T, AggregateObjectsQuery<T>>,
        Projectable<T>,
        TypeResultQuery<T> {


}