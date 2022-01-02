package github.sql.dsl.query.suport.jpa;

import github.sql.dsl.query.api.query.ObjectsTypeQuery;
import github.sql.dsl.query.api.query.TypeQuery;
import github.sql.dsl.query.suport.ResultsFactory;
import github.sql.dsl.query.suport.CriteriaQuery;

import javax.persistence.EntityManager;

public class JpaResultsFactory implements ResultsFactory {

    private final EntityManager entityManager;

    public JpaResultsFactory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public <T> TypeQuery<T> results(CriteriaQuery criteriaQuery, Class<T> type) {
        return new JpaTypeQuery<>(entityManager, type, criteriaQuery);
    }

    @Override
    public ObjectsTypeQuery arrayResults(CriteriaQuery criteriaQuery, Class<?> type) {
        return new JpaTypeQuery<>(entityManager, type, criteriaQuery);
    }


}
