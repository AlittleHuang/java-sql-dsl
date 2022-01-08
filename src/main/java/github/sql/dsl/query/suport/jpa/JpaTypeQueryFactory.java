package github.sql.dsl.query.suport.jpa;

import github.sql.dsl.query.api.query.ArrayResultQuery;
import github.sql.dsl.query.api.query.EntityResultQuery;
import github.sql.dsl.query.api.query.ProjectionResultQuery;
import github.sql.dsl.query.suport.CriteriaQuery;
import github.sql.dsl.query.suport.TypeQueryFactory;

import javax.persistence.EntityManager;

public class JpaTypeQueryFactory implements TypeQueryFactory {

    private final EntityManager entityManager;

    public JpaTypeQueryFactory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public <T> EntityResultQuery<T> getTypeQuery(CriteriaQuery criteriaQuery, Class<T> type) {
        return new JpaEntityResultQuery<>(entityManager, type, criteriaQuery);
    }

    @Override
    public <T, R> ProjectionResultQuery<R> getProjectionQuery(CriteriaQuery criteriaQuery,
                                                              Class<T> type,
                                                              Class<R> projectionType) {
        // TODO
        return null;
    }

    @Override
    public ArrayResultQuery getObjectsTypeQuery(CriteriaQuery criteriaQuery, Class<?> type) {
        return new JpaEntityResultQuery<>(entityManager, type, criteriaQuery);
    }


}
