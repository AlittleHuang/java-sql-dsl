package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.builder.*;

public interface WhereAssembler<T> extends
        PredicateAssembler<T, WhereAssembler<T>>,
        Fetchable<T, EntityQuery<T>>,
        Sortable<T, WhereAssembler<T>>,
        Groupable<T, ArrayQuery<T>>,
        Selectable<T, ArrayQuery<T>>,
        Projectable<T>,
        TypeResultQuery<T> {


}
