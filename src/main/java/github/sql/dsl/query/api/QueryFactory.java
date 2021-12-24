package github.sql.dsl.query.api;

public interface QueryFactory {

    <T> Query<T> from(Class<T> type);

}
