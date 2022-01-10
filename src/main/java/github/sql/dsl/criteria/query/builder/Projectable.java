package github.sql.dsl.criteria.query.builder;

public interface Projectable<T> {

    <R> ProjectionResultQuery<R> projected(Class<R> projectionType);

}
