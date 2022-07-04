package github.sql.dsl.internal.jpa;

import github.sql.dsl.criteria.query.builder.ResultBuilder;
import github.sql.dsl.criteria.query.support.SqlCriteriaQuery;
import github.sql.dsl.criteria.query.support.TypeQueryFactory;

import javax.persistence.EntityManager;

public class JpaTypeQueryFactory implements TypeQueryFactory {

    private final EntityManager entityManager;

    public JpaTypeQueryFactory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public <T> ResultBuilder<T> getEntityResultQuery(SqlCriteriaQuery criteriaQuery, Class<T> type) {
        return new JpaEntityResultBuilder<>(entityManager, type, criteriaQuery);
    }

    @Override
    public ResultBuilder<Object[]> getObjectsTypeQuery(SqlCriteriaQuery criteriaQuery, Class<?> type) {
        return new JpaObjectsResultBuilder<>(entityManager, type, criteriaQuery);
    }


}
