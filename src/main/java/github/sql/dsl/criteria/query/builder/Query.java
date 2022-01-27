package github.sql.dsl.criteria.query.builder;

import github.sql.dsl.criteria.query.builder.combination.*;

public interface Query<T> extends
        Whereable<T, WhereAssembler<T>>,
        Fetchable<T, EntityResultBuilder<T>>,
        Sortable<T, WhereAssembler<T>>,
        Groupable<T, GroupByBuilder<T>>,
        Selectable<T, ObjectsResultBuilder<T>>,
        AggregateSelectable<T, AggregateObjectsResultBuilder<T>>,
        Projectable<T>,
        ResultBuilder<T> {


}
