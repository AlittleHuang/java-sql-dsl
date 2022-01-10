package github.sql.dsl.internal.jpa;

import github.sql.dsl.criteria.query.builder.ArrayResultQuery;
import github.sql.dsl.criteria.query.builder.EntityResultQuery;
import github.sql.dsl.criteria.query.support.CriteriaQuery;
import github.sql.dsl.criteria.query.support.TypeQueryFactory;

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
    public ArrayResultQuery getObjectsTypeQuery(CriteriaQuery criteriaQuery, Class<?> type) {
        return new JpaEntityResultQuery<>(entityManager, type, criteriaQuery);
    }


}
