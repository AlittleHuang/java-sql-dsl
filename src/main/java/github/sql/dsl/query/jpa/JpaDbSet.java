package github.sql.dsl.query.jpa;

import github.sql.dsl.query.api.suport.DbSets;

import javax.persistence.EntityManager;

public class JpaDbSet extends DbSets {

    public JpaDbSet(EntityManager entityManager) {
        super(new JpaTypeQueryFactory(entityManager));
    }

}
