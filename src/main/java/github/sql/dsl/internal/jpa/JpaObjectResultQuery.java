package github.sql.dsl.internal.jpa;

import github.sql.dsl.criteria.query.builder.TypeResultQuery;
import github.sql.dsl.criteria.query.support.CriteriaQuery;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaObjectResultQuery<T> extends JpaResultQuery<T> implements TypeResultQuery<Object[]> {

    public JpaObjectResultQuery(EntityManager entityManager, Class<T> type, CriteriaQuery criteria) {
        super(entityManager, type, criteria);
    }

    @Override
    public List<Object[]> getResultList(int offset, int maxResult) {
        return new Builder<>(Object[].class).getObjectsList(offset, maxResult);
    }


}
