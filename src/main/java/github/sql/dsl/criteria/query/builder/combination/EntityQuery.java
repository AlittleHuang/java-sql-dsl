package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.builder.Fetchable;
import github.sql.dsl.criteria.query.builder.PredicateCombinable;
import github.sql.dsl.criteria.query.builder.Sortable;
import github.sql.dsl.criteria.query.builder.TypeResultQuery;

public interface EntityQuery<T> extends
        PredicateCombinable<T, EntityQuery<T>>,
        Fetchable<T, EntityQuery<T>>,
        Sortable<T, EntityQuery<T>>,
        TypeResultQuery<T> {

}
