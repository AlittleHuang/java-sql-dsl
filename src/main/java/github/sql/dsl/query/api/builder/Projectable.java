package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.query.ProjectionQuery;

public interface Projectable<T> {

    <R> ProjectionQuery<R> projected(Class<R> projectionType);

}
