package github.sql.dsl.criteria.query.builder;

public interface Projectable<T> {

    <R> ResultBuilder<R> projected(Class<R> projectionType);

}
