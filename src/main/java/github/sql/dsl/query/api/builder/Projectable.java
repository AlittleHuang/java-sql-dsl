package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.query.ProjectionResultQuery;

public interface Projectable<T> {

    <R> ProjectionResultQuery<R> projected(Class<R> projectionType);

}
