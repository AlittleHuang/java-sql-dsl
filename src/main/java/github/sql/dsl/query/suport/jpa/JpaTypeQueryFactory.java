package github.sql.dsl.query.suport.jpa;

import github.sql.dsl.query.api.query.ObjectsTypeQuery;
import github.sql.dsl.query.api.query.ProjectionQuery;
import github.sql.dsl.query.api.query.TypeQuery;
import github.sql.dsl.query.suport.CriteriaQuery;
import github.sql.dsl.query.suport.TypeQueryFactory;

import javax.persistence.EntityManager;

public class JpaTypeQueryFactory implements TypeQueryFactory {

    private final EntityManager entityManager;

    public JpaTypeQueryFactory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public <T> TypeQuery<T> getTypeQuery(CriteriaQuery criteriaQuery, Class<T> type) {
        return new JpaTypeQuery<>(entityManager, type, criteriaQuery);
    }

    @Override
    public <T, R> ProjectionQuery<R> getProjectionQuery(CriteriaQuery criteriaQuery,
                                                        Class<T> type,
                                                        Class<R> projectionType) {
        // TODO
        return null;
    }

    @Override
    public ObjectsTypeQuery getObjectsTypeQuery(CriteriaQuery criteriaQuery, Class<?> type) {
        return new JpaTypeQuery<>(entityManager, type, criteriaQuery);
    }


}
