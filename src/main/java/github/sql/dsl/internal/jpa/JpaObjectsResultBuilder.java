package github.sql.dsl.internal.jpa;

import github.sql.dsl.criteria.query.builder.ResultBuilder;
import github.sql.dsl.criteria.query.support.CriteriaQuery;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaObjectsResultBuilder<T> extends JpaResultQuery<T> implements ResultBuilder<Object[]> {

    public JpaObjectsResultBuilder(EntityManager entityManager, Class<T> type, CriteriaQuery criteria) {
        super(entityManager, type, criteria);
    }

    @Override
    public List<Object[]> getList(int offset, int maxResult) {
        return new Builder<>(Object[].class).getObjectsList(offset, maxResult);
    }


}
