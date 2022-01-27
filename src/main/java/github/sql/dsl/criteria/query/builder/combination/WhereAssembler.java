package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.builder.*;

public interface WhereAssembler<T> extends
        PredicateAssembler<T, WhereAssembler<T>>,
        Fetchable<T, EntityResultBuilder<T>>,
        Sortable<T, WhereAssembler<T>>,
        Groupable<T, GroupByBuilder<T>>,
        Selectable<T, ObjectsResultBuilder<T>>,
        Projectable<T>,
        ResultBuilder<T> {


}
