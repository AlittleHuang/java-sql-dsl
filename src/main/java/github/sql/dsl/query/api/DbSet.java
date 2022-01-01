package github.sql.dsl.query.api;

public interface DbSet {

    <T> QueryBuilder<T> from(Class<T> type);

}
