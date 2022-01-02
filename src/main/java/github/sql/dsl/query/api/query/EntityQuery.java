package github.sql.dsl.query.api.query;

import github.sql.dsl.query.api.builder.Fetchable;
import github.sql.dsl.query.api.builder.PredicateCombinable;
import github.sql.dsl.query.api.builder.Sortable;

public interface EntityQuery<T> extends
        PredicateCombinable<T, EntityQuery<T>>,
        Fetchable<T, EntityQuery<T>>,
        Sortable<T, EntityQuery<T>>,
        TypeQuery<T> {

}
