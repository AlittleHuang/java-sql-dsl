package github.sql.dsl.query.suport.jdbc.sql;

import github.sql.dsl.query.api.query.ProjectionResultQuery;

import java.util.List;

public class JdbcProjectionResultQuery<T, R> implements ProjectionResultQuery<R> {

    private final PreparedSqlExecutor executor;
    private final PreparedSqlBuilder builder;
    private final Class<T> entityType;
    private final Class<R> projectionType;

    public JdbcProjectionResultQuery(PreparedSqlExecutor executor,
                                     PreparedSqlBuilder builder,
                                     Class<T> entityType,
                                     Class<R> projectionType) {
        this.executor = executor;
        this.builder = builder;
        this.entityType = entityType;
        this.projectionType = projectionType;
    }

    @Override
    public int count() {
        return executor.count(builder.count(), entityType);
    }

    @Override
    public List<R> getResultList(int offset, int maxResul) {
        SelectedPreparedSql sql = builder.getProjectionList(offset, maxResul, projectionType);
        return executor.getProjectionList(sql, entityType, projectionType);
    }

    @Override
    public boolean exist(int offset) {
        return executor.exist(builder.exist(offset), entityType);
    }

}
