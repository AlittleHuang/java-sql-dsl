package github.sql.dsl.internal;

import github.sql.dsl.criteria.query.QueryBuilder;
import github.sql.dsl.internal.jdbc.JdbcQueryBuilder;
import github.sql.dsl.internal.jdbc.mysql.MysqlSqlBuilder;
import github.sql.dsl.internal.jdbc.sql.SqlExecutor;
import github.sql.dsl.internal.jpa.JpaQueryBuilder;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

public class QueryBuilders {

    public static QueryBuilder mysql(SqlExecutor sqlExecutor) {
        return new JdbcQueryBuilder(sqlExecutor, MysqlSqlBuilder::new);
    }

    public static QueryBuilder mysql(DataSource dataSource) {
        return new JdbcQueryBuilder(SqlExecutor.fromDatasource(dataSource), MysqlSqlBuilder::new);
    }

    public static QueryBuilder jpa(EntityManager entityManager) {
        return new JpaQueryBuilder(entityManager);
    }

}
