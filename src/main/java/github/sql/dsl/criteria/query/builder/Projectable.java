package github.sql.dsl.criteria.query.builder;

public interface Projectable<T> {

    <R> TypeResultQuery<R> projected(Class<R> projectionType);

}
