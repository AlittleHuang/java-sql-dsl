package github.sql.dsl.query.api.suport;

import github.sql.dsl.query.QueryBuilder;
import github.sql.dsl.query.api.Query;
import github.sql.dsl.query.api.suport.builder.query.QueryImpl;
import github.sql.dsl.query.jdbc.JdbcDbSet;
import github.sql.dsl.query.jdbc.mysql.MysqlSqlBuilder;
import github.sql.dsl.query.jpa.JpaDbSet;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

public class DbSets implements QueryBuilder {

    private final TypeQueryFactory typeQueryFactory;

    public DbSets(TypeQueryFactory typeQueryFactory) {
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
