package github.sql.dsl.query.suport;

import github.sql.dsl.query.api.query.ObjectsTypeQuery;
import github.sql.dsl.query.api.query.ProjectionQuery;
import github.sql.dsl.query.api.query.TypeQuery;

public interface TypeQueryFactory {

    <T> TypeQuery<T> getTypeQuery(CriteriaQuery criteriaQuery, Class<T> type);

    <T, R> ProjectionQuery<R> getProjectionQuery(CriteriaQuery criteriaQuery, Class<T> type, Class<R> projectionType);

    ObjectsTypeQuery getObjectsTypeQuery(CriteriaQuery criteriaQuery, Class<?> type);


}
