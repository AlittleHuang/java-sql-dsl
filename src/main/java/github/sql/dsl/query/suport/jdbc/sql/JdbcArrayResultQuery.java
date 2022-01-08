package github.sql.dsl.query.suport.jdbc.sql;

import github.sql.dsl.query.api.query.ArrayResultQuery;

import java.util.List;

public class JdbcArrayResultQuery implements ArrayResultQuery {

    private final PreparedSqlExecutor executor;
    private final PreparedSqlBuilder builder;
    private final Class<?> entityType;

    public JdbcArrayResultQuery(PreparedSqlExecutor executor, PreparedSqlBuilder builder, Class<?> entityType) {
        this.executor = executor;
        this.builder = builder;
        this.entityType = entityType;
    }

    @Override
    public List<Object[]> getObjectsList(int offset, int maxResult) {
        return executor.listResult(builder.getObjectsList(offset, maxResult), entityType);
    }
}
