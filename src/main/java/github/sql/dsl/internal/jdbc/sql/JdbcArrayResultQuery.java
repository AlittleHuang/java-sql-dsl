package github.sql.dsl.internal.jdbc.sql;

import github.sql.dsl.criteria.query.builder.TypeResultQuery;

import java.util.List;

public class JdbcArrayResultQuery implements TypeResultQuery<Object[]> {

    private final PreparedSqlExecutor executor;
    private final PreparedSqlBuilder builder;
    private final Class<?> entityType;

    public JdbcArrayResultQuery(PreparedSqlExecutor executor, PreparedSqlBuilder builder, Class<?> entityType) {
        this.executor = executor;
        this.builder = builder;
        this.entityType = entityType;
    }

    @Override
    public List<Object[]> getResultList(int offset, int maxResult) {
        return executor.listResult(builder.getObjectsList(offset, maxResult), entityType);
    }

    @Override
    public int count() {
        return executor.count(builder.count(), entityType);
    }

    @Override
    public boolean exist(int offset) {
        return executor.exist(builder.exist(offset), entityType);
    }

}
