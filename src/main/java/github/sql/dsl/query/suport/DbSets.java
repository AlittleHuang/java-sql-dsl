package github.sql.dsl.query.suport;

import github.sql.dsl.query.DbSet;
import github.sql.dsl.query.api.Query;
import github.sql.dsl.query.suport.builder.query.QueryImpl;
import github.sql.dsl.query.suport.jdbc.JdbcDbSet;
import github.sql.dsl.query.suport.jdbc.mysql.MysqlSqlBuilder;
import github.sql.dsl.query.suport.jpa.JpaDbSet;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

public class DbSets implements DbSet {

    private final TypeQueryFactory typeQueryFactory;

    public DbSets(TypeQueryFactory typeQueryFactory) {
        this.typeQueryFactory = typeQueryFactory;
    }

    @Override
    public <T> Query<T> from(Class<T> type) {
        return new QueryImpl<>(typeQueryFactory, type, null);
    }

    public static DbSet mysql(DataSource source) {
        return new JdbcDbSet(source, MysqlSqlBuilder::new);
    }

    public static DbSet jpa(EntityManager entityManager) {
        return new JpaDbSet(entityManager);
    }

}