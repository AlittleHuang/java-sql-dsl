package github.sql.dsl.query.api.query;

import github.sql.dsl.query.api.builder.AggregateSelectable;
import github.sql.dsl.query.api.builder.Groupable;
import github.sql.dsl.query.api.builder.Sortable;

public interface AggregateObjectsQuery<T> extends
        Whereable<T, ArrayQuery<T>>,
        Sortable<T, ArrayQuery<T>>,
        Groupable<T, ArrayQuery<T>>,
        AggregateSelectable<T, AggregateObjectsQuery<T>>,
        ArrayResultQuery {


}
