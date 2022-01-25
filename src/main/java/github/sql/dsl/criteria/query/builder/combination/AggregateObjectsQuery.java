package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.builder.AggregateSelectable;
import github.sql.dsl.criteria.query.builder.Groupable;
import github.sql.dsl.criteria.query.builder.Sortable;
import github.sql.dsl.criteria.query.builder.TypeResultQuery;

public interface AggregateObjectsQuery<T> extends
        Whereable<T, ArrayQuery<T>>,
        Sortable<T, ArrayQuery<T>>,
        Groupable<T, GroupByBuilder<T>>,
        AggregateSelectable<T, AggregateObjectsQuery<T>>,
        TypeResultQuery<Object[]> {


}
