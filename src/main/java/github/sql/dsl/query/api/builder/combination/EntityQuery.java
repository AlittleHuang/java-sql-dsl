package github.sql.dsl.query.api.builder.combination;

import github.sql.dsl.query.api.builder.EntityResultQuery;
import github.sql.dsl.query.api.builder.Fetchable;
import github.sql.dsl.query.api.builder.PredicateCombinable;
import github.sql.dsl.query.api.builder.Sortable;

public interface EntityQuery<T> extends
        PredicateCombinable<T, EntityQuery<T>>,
        Fetchable<T, EntityQuery<T>>,
        Sortable<T, EntityQuery<T>>,
        EntityResultQuery<T> {

}
