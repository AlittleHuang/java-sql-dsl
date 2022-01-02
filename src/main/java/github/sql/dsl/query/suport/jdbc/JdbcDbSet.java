package github.sql.dsl.query.suport.jdbc;

import github.sql.dsl.query.suport.DbSets;
import github.sql.dsl.query.suport.ResultsFactory;
import github.sql.dsl.query.suport.jdbc.sql.*;

import javax.sql.DataSource;

public class JdbcDbSet extends DbSets {

    public JdbcDbSet(ResultsFactory resultsFactory) {
        super(resultsFactory);
    }

    public JdbcDbSet(PreparedSqlExecutor executor,
                     SqlBuilderFactory sqlBuilderFactory) {
        this(new JdbcQueryResultsFactory(executor, sqlBuilderFactory));
    }


    public JdbcDbSet(SqlExecutor sqlExecutor,
                     SqlBuilderFactory sqlBuilderFactory) {
        this(new SqlExecutorImpl(sqlExecutor), sqlBuilderFactory);
    }


    public JdbcDbSet(ConnectionProvider connectionProvider,
                     SqlBuilderFactory sqlBuilderFactory) {
        this(SqlExecutor.fromConnectionSupplier(connectionProvider), sqlBuilderFactory);
    }


    public JdbcDbSet(DataSource dataSource,
                     SqlBuilderFactory sqlBuilderFactory) {
        this(dataSource::getConnection, sqlBuilderFactory);
    }

}
