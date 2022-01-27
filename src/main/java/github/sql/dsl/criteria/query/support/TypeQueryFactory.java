package github.sql.dsl.criteria.query.support;

import github.sql.dsl.criteria.query.builder.ResultBuilder;

public interface TypeQueryFactory {

    <T> ResultBuilder<T> getEntityResultQuery(CriteriaQuery criteriaQuery, Class<T> type);

    default <T, R> ResultBuilder<R> getProjectionQuery(CriteriaQuery criteriaQuery,
                                                       Class<T> type,
                                                       Class<R> projectionType) {
        return new ProjectionResultBuilder<>(this, criteriaQuery, type, projectionType);
    }

    ResultBuilder<Object[]> getObjectsTypeQuery(CriteriaQuery criteriaQuery, Class<?> type);

}
