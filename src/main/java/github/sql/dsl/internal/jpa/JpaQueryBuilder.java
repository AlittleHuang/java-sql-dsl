package github.sql.dsl.internal.jpa;

import github.sql.dsl.criteria.query.support.builder.query.AbstractQueryBuilder;

import javax.persistence.EntityManager;

public class JpaQueryBuilder extends AbstractQueryBuilder {

    public JpaQueryBuilder(EntityManager entityManager) {
        super(new JpaTypeQueryFactory(entityManager));
    }

}
