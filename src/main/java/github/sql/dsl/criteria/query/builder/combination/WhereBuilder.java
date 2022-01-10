package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.builder.*;

public interface WhereBuilder<T> extends
        PredicateCombinable<T, WhereBuilder<T>>,
        Fetchable<T, EntityQuery<T>>,
        Sortable<T, WhereBuilder<T>>,
        Groupable<T, ArrayQuery<T>>,
        Selectable<T, ArrayQuery<T>>,
        Projectable<T>,
        EntityResultQuery<T> {


}
