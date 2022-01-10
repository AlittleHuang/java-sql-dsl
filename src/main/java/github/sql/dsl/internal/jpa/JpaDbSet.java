package github.sql.dsl.internal.jpa;

import github.sql.dsl.criteria.query.support.QueryBuilders;

import javax.persistence.EntityManager;

public class JpaDbSet extends QueryBuilders {

    public JpaDbSet(EntityManager entityManager) {
        super(new JpaTypeQueryFactory(entityManager));
    }

}
