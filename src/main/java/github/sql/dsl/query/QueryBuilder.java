package github.sql.dsl.query;

import github.sql.dsl.query.api.Query;

public interface QueryBuilder {

    <T> Query<T> query(Class<T> type);

}
