package github.sql.dsl.criteria.query.support;

import github.sql.dsl.criteria.query.QueryBuilder;
import github.sql.dsl.criteria.query.builder.Query;
import github.sql.dsl.criteria.query.support.builder.query.QueryImpl;
import github.sql.dsl.internal.jdbc.JdbcDbSet;
import github.sql.dsl.internal.jdbc.mysql.MysqlSqlBuilder;
import github.sql.dsl.internal.jpa.JpaDbSet;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

public class QueryBuilders implements QueryBuilder {

    private final TypeQueryFactory typeQueryFactory;

    public QueryBuilders(TypeQueryFactory typeQueryFactory) {
        this.typeQueryFactory = typeQueryFactory;
    }

    @Override
    public <T> Query<T> query(Class<T> type) {
        return new QueryImpl<>(typeQueryFactory, type, null);
    }

    public static QueryBuilder mysql(DataSource source) {
        return new JdbcDbSet(source, MysqlSqlBuilder::new);
    }

    public static QueryBuilder jpa(EntityManager entityManager) {
        return new JpaDbSet(entityManager);
    }

}
