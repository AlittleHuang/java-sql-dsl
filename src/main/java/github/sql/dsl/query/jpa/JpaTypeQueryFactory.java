package github.sql.dsl.query.jpa;

import github.sql.dsl.query.api.builder.ArrayResultQuery;
import github.sql.dsl.query.api.builder.EntityResultQuery;
import github.sql.dsl.query.api.suport.CriteriaQuery;
import github.sql.dsl.query.api.suport.TypeQueryFactory;

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
