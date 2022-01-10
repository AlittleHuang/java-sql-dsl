package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.builder.AggregateSelectable;
import github.sql.dsl.criteria.query.builder.ArrayResultQuery;
import github.sql.dsl.criteria.query.builder.Groupable;
import github.sql.dsl.criteria.query.builder.Sortable;

public interface AggregateObjectsQuery<T> extends
        Whereable<T, ArrayQuery<T>>,
        Sortable<T, ArrayQuery<T>>,
        Groupable<T, ArrayQuery<T>>,
        AggregateSelectable<T, AggregateObjectsQuery<T>>,
        ArrayResultQuery {


}
