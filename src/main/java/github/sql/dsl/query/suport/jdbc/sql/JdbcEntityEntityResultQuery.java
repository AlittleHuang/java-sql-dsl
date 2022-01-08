package github.sql.dsl.query.suport.jdbc.sql;

import github.sql.dsl.query.api.query.EntityResultQuery;

import java.util.List;

public class JdbcEntityEntityResultQuery<T> implements EntityResultQuery<T> {

    private final PreparedSqlExecutor executor;
    private final PreparedSqlBuilder builder;
    private final Class<T> entityType;

    public JdbcEntityEntityResultQuery(PreparedSqlExecutor executor, PreparedSqlBuilder builder, Class<T> entityType) {
        this.executor = executor;
        this.builder = builder;
        this.entityType = entityType;
    }

    @Override
    public int count() {
        return executor.count(builder.count(), entityType);
    }

    @Override
    public List<T> getResultList(int offset, int maxResul) {
        return executor.getEntityList(builder.getEntityList(offset, maxResul), entityType);
    }

    @Override
    public boolean exist(int offset) {
        return executor.exist(builder.exist(offset), entityType);
    }

}
