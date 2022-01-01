package github.sql.dsl.query.suport;

import github.sql.dsl.query.api.DbSet;
import github.sql.dsl.query.suport.common.QueryBuilder;
import github.sql.dsl.query.suport.common.ResultsFactory;
import github.sql.dsl.query.suport.jdbc.JdbcDbSet;
import github.sql.dsl.query.suport.jdbc.mysql.MysqlSqlBuilder;
import github.sql.dsl.query.suport.jpa.JpaDbSet;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

public class DbSets implements DbSet {

    private final ResultsFactory resultsFactory;

    public DbSets(ResultsFactory resultsFactory) {
        this.resultsFactory = resultsFactory;
    }

    @Override
    public <T> github.sql.dsl.query.api.QueryBuilder<T> from(Class<T> type) {
        return new QueryBuilder<>(type, resultsFactory);
    }

    public static DbSet mysql(DataSource source) {
        return new JdbcDbSet(source, MysqlSqlBuilder::new);
    }

    public static DbSet jpa(EntityManager entityManager) {
        return new JpaDbSet(entityManager);
    }

}
