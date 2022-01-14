package github.sql.dsl.criteria.query.builder;

import github.sql.dsl.criteria.query.builder.combination.*;

public interface Query<T> extends
        Whereable<T, WhereAssembler<T>>,
        Fetchable<T, EntityQuery<T>>,
        Sortable<T, WhereAssembler<T>>,
        Groupable<T, ArrayQuery<T>>,
        Selectable<T, ArrayQuery<T>>,
        AggregateSelectable<T, AggregateObjectsQuery<T>>,
        Projectable<T>,
        TypeResultQuery<T> {


}
