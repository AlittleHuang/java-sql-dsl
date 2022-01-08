package github.sql.dsl.query.api.query;

import github.sql.dsl.query.api.builder.Groupable;
import github.sql.dsl.query.api.builder.Selectable;
import github.sql.dsl.query.api.builder.Sortable;

public interface ArrayQuery<T> extends
        Whereable<T, ArrayQuery<T>>,
        Sortable<T, ArrayQuery<T>>,
        Groupable<T, ArrayQuery<T>>,
        Selectable<T, ArrayQuery<T>>,
        ArrayResultQuery {


}
