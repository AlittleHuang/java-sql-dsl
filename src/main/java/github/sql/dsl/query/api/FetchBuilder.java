package github.sql.dsl.query.api;

public interface FetchBuilder<T> extends TypeQuery<T> {

    FetchBuilder<T> fetch(EntityAttributeBridge<T, ?> column);

}
