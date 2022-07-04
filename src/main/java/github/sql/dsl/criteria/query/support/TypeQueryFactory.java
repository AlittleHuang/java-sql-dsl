package github.sql.dsl.criteria.query.support;

import github.sql.dsl.criteria.query.builder.ResultBuilder;

public interface TypeQueryFactory {

    <T> ResultBuilder<T> getEntityResultQuery(SqlCriteriaQuery criteriaQuery, Class<T> type);

    default <T, R> ResultBuilder<R> getProjectionQuery(SqlCriteriaQuery criteriaQuery,
                                                       Class<T> type,
                                                       Class<R> projectionType) {
        return new ProjectionResultBuilder<>(this, criteriaQuery, type, projectionType);
    }

    ResultBuilder<Object[]> getObjectsTypeQuery(SqlCriteriaQuery criteriaQuery, Class<?> type);

}
