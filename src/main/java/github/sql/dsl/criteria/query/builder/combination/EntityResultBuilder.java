package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.builder.Fetchable;
import github.sql.dsl.criteria.query.builder.PredicateAssembler;
import github.sql.dsl.criteria.query.builder.ResultBuilder;
import github.sql.dsl.criteria.query.builder.Sortable;

public interface EntityResultBuilder<T> extends
        PredicateAssembler<T, EntityResultBuilder<T>>,
        Fetchable<T, EntityResultBuilder<T>>,
        Sortable<T, EntityResultBuilder<T>>,
        ResultBuilder<T> {

}
