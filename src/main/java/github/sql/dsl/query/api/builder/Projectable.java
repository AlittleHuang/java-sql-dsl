package github.sql.dsl.query.api.builder;

public interface Projectable<T> {

    <R> ProjectionResultQuery<R> projected(Class<R> projectionType);

}
