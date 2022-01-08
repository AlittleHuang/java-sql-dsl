package github.sql.dsl.query.suport.builder.query;

import github.sql.dsl.query.api.query.ProjectionResultQuery;
import github.sql.dsl.query.suport.jdbc.sql.PreparedSqlBuilder;
import github.sql.dsl.query.suport.jdbc.sql.PreparedSqlExecutor;

import java.util.List;

public class ProjectionResultQueryImpl<T, R> implements ProjectionResultQuery<R> {

    private final PreparedSqlExecutor executor;
    private final PreparedSqlBuilder builder;
    private final Class<T> entityType;
    protected final Class<R> projectionType;

    public ProjectionResultQueryImpl(PreparedSqlExecutor executor,
                                     PreparedSqlBuilder builder,
                                     Class<T> entityType,
                                     Class<R> projectionType) {
        this.executor = executor;
        this.builder = builder;
        this.entityType = entityType;
        this.projectionType = projectionType;
    }


    @Override
    public List<R> getResultList(int offset, int maxResul) {
        return executor.getProjectionList(builder.getProjectionList(offset, maxResul, projectionType), entityType, projectionType);
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
