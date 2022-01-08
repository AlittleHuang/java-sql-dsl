package github.sql.dsl.query;

import github.sql.dsl.query.api.Query;

public interface DbSet {

    <T> Query<T> create(Class<T> type);

}
