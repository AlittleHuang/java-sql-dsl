package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.builder.AggregateSelectable;
import github.sql.dsl.criteria.query.builder.Groupable;
import github.sql.dsl.criteria.query.builder.ResultBuilder;

public interface AggregateObjectsResultBuilder<T> extends
        Whereable<T, ObjectsResultBuilder<T>>,
        Groupable<T, GroupByBuilder<T>>,
        AggregateSelectable<T, AggregateObjectsResultBuilder<T>>,
        ResultBuilder<Object[]> {


}
