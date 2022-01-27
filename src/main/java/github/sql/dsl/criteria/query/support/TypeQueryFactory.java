package github.sql.dsl.criteria.query.support;

import github.sql.dsl.criteria.query.builder.TypeResultQuery;

public interface TypeQueryFactory {

    <T> TypeResultQuery<T> getEntityResultQuery(CriteriaQuery criteriaQuery, Class<T> type);

    default <T, R> TypeResultQuery<R> getProjectionQuery(CriteriaQuery criteriaQuery,
                                                         Class<T> type,
                                                         Class<R> projectionType) {
        return new DefaultTypeResultQuery<>(this, criteriaQuery, type, projectionType);
    }

    TypeResultQuery<Object[]> getObjectsTypeQuery(CriteriaQuery criteriaQuery, Class<?> type);

}
