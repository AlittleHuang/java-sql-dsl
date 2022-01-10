package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.builder.EntityResultQuery;
import github.sql.dsl.criteria.query.builder.Fetchable;
import github.sql.dsl.criteria.query.builder.PredicateCombinable;
import github.sql.dsl.criteria.query.builder.Sortable;

public interface EntityQuery<T> extends
        PredicateCombinable<T, EntityQuery<T>>,
        Fetchable<T, EntityQuery<T>>,
        Sortable<T, EntityQuery<T>>,
        EntityResultQuery<T> {

}
