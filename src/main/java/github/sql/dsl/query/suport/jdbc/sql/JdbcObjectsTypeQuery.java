package github.sql.dsl.query.suport.jdbc.sql;

import github.sql.dsl.query.api.ObjectsTypeQuery;

import java.util.List;

public class JdbcObjectsTypeQuery implements ObjectsTypeQuery {

    private final PreparedSqlExecutor executor;
    private final PreparedSqlBuilder builder;
    private final Class<?> entityType;

    public JdbcObjectsTypeQuery(PreparedSqlExecutor executor, PreparedSqlBuilder builder, Class<?> entityType) {
        this.executor = executor;
        this.builder = builder;
        this.entityType = entityType;
    }

    @Override
    public List<Object[]> getObjectsList(int offset, int maxResult) {
        return executor.listResult(builder.getObjectsList(offset, maxResult), entityType);
    }
}
