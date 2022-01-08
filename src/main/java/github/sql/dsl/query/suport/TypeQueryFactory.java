package github.sql.dsl.query.suport;

import github.sql.dsl.query.api.query.ArrayResultQuery;
import github.sql.dsl.query.api.query.EntityResultQuery;
import github.sql.dsl.query.api.query.ProjectionResultQuery;

public interface TypeQueryFactory {

    <T> EntityResultQuery<T> getTypeQuery(CriteriaQuery criteriaQuery, Class<T> type);

    <T, R> ProjectionResultQuery<R> getProjectionQuery(CriteriaQuery criteriaQuery, Class<T> type, Class<R> projectionType);

    ArrayResultQuery getObjectsTypeQuery(CriteriaQuery criteriaQuery, Class<?> type);


}
