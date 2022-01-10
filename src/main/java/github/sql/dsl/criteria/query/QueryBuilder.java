package github.sql.dsl.criteria.query;

import github.sql.dsl.criteria.query.builder.Query;

public interface QueryBuilder {

    <T> Query<T> query(Class<T> type);

}
