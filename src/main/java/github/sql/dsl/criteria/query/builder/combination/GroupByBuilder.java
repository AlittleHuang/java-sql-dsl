package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.builder.AggregateSelectable;
import github.sql.dsl.criteria.query.builder.Groupable;
import github.sql.dsl.criteria.query.builder.Selectable;

public interface GroupByBuilder<T> extends
        Groupable<T, GroupByBuilder<T>>,
        Selectable<T, ObjectsResultBuilder<T>>,
        AggregateSelectable<T, AggregateObjectsResultBuilder<T>> {


}
