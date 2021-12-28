package github.sql.dsl.query.api;

public interface QueryFactory {

    <T> QueryBuilder<T> from(Class<T> type);

}
