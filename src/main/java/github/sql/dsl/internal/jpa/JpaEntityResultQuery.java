package github.sql.dsl.internal.jpa;

import github.sql.dsl.criteria.query.builder.TypeResultQuery;
import github.sql.dsl.criteria.query.support.CriteriaQuery;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaEntityResultQuery<T> extends JpaResultQuery<T> implements TypeResultQuery<T> {

    public JpaEntityResultQuery(EntityManager entityManager, Class<T> type, CriteriaQuery criteria) {
        super(entityManager, type, criteria);
    }

    @Override
    public List<T> getResultList(int offset, int maxResult) {
        return new Builder<>(entityType).getResultList(offset, maxResult);
    }

}
