package github.sql.dsl.query.api;

import github.sql.dsl.query.api.column.EntityColumn;

public interface FetchBuilder<T> extends Results<T> {

    FetchBuilder<T> fetch(EntityColumn<T, ?> column);

}
