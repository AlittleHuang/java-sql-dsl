package github.sql.dsl.internal.jpa;

import github.sql.dsl.criteria.query.builder.ResultBuilder;
import github.sql.dsl.criteria.query.support.SqlCriteriaQuery;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaEntityResultBuilder<T> extends JpaResultQuery<T> implements ResultBuilder<T> {

    public JpaEntityResultBuilder(EntityManager entityManager, Class<T> type, SqlCriteriaQuery criteria) {
        super(entityManager, type, criteria);
    }

    @Override
    public List<T> getList(int offset, int maxResult) {
        return new Builder<>(entityType).getResultList(offset, maxResult);
    }

}
