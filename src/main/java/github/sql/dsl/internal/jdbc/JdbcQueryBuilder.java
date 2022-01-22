package github.sql.dsl.internal.jdbc;

import github.sql.dsl.criteria.query.support.TypeQueryFactory;
import github.sql.dsl.criteria.query.support.builder.query.AbstractQueryBuilder;
import github.sql.dsl.internal.jdbc.sql.*;

public class JdbcQueryBuilder extends AbstractQueryBuilder {

    public JdbcQueryBuilder(TypeQueryFactory typeQueryFactory) {
        super(typeQueryFactory);
    }

    public JdbcQueryBuilder(PreparedSqlExecutor executor,
                            SqlBuilderFactory sqlBuilderFactory) {
        this(new JdbcQueryTypeQueryFactory(executor, sqlBuilderFactory));
    }


    public JdbcQueryBuilder(SqlExecutor sqlExecutor,
                            SqlBuilderFactory sqlBuilderFactory) {
        this(new SqlExecutorImpl(sqlExecutor), sqlBuilderFactory);
    }

}
