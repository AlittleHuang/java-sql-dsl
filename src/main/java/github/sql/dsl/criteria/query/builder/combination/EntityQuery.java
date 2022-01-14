package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.builder.Fetchable;
import github.sql.dsl.criteria.query.builder.PredicateAssembler;
import github.sql.dsl.criteria.query.builder.Sortable;
import github.sql.dsl.criteria.query.builder.TypeResultQuery;

public interface EntityQuery<T> extends
        PredicateAssembler<T, EntityQuery<T>>,
        Fetchable<T, EntityQuery<T>>,
        Sortable<T, EntityQuery<T>>,
        TypeResultQuery<T> {

}
