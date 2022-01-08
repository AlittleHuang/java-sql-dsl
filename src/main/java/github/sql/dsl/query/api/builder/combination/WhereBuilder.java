package github.sql.dsl.query.api.builder.combination;

import github.sql.dsl.query.api.builder.*;

public interface WhereBuilder<T> extends
        PredicateCombinable<T, WhereBuilder<T>>,
        Fetchable<T, EntityQuery<T>>,
        Sortable<T, WhereBuilder<T>>,
        Groupable<T, ArrayQuery<T>>,
        Selectable<T, ArrayQuery<T>>,
        Projectable<T>,
        EntityResultQuery<T> {


}
