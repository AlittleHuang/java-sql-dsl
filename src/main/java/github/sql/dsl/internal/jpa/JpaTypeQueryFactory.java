package github.sql.dsl.internal.jpa;

import github.sql.dsl.criteria.query.builder.TypeResultQuery;
import github.sql.dsl.criteria.query.support.CriteriaQuery;
import github.sql.dsl.criteria.query.support.TypeQueryFactory;

import javax.persistence.EntityManager;

public class JpaTypeQueryFactory implements TypeQueryFactory {

    private final EntityManager entityManager;

    public JpaTypeQueryFactory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public <T> TypeResultQuery<T> getEntityResultQuery(CriteriaQuery criteriaQuery, Class<T> type) {
        return new JpaEntityResultQuery<>(entityManager, type, criteriaQuery);
    }

    @Override
    public TypeResultQuery<Object[]> getObjectsTypeQuery(CriteriaQuery criteriaQuery, Class<?> type) {
        return new JpaObjectResultQuery<>(entityManager, type, criteriaQuery);
    }


}
