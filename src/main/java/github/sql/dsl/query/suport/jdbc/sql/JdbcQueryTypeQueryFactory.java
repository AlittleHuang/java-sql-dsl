package github.sql.dsl.query.suport.jdbc.sql;

import github.sql.dsl.query.api.query.ArrayResultQuery;
import github.sql.dsl.query.api.query.EntityResultQuery;
import github.sql.dsl.query.api.query.ProjectionResultQuery;
import github.sql.dsl.query.suport.CriteriaQuery;
import github.sql.dsl.query.suport.TypeQueryFactory;
import github.sql.dsl.query.suport.builder.query.ProjectionResultQueryImpl;

public class JdbcQueryTypeQueryFactory implements TypeQueryFactory {
    private final PreparedSqlExecutor executor;
    private final SqlBuilderFactory sqlBuilder;

    public JdbcQueryTypeQueryFactory(PreparedSqlExecutor executor,
                                     SqlBuilderFactory sqlBuilder) {
        this.executor = executor;
        this.sqlBuilder = sqlBuilder;
    }

    @Override
    public <T> EntityResultQuery<T> getTypeQuery(CriteriaQuery criteria, Class<T> type) {
        return new JdbcEntityEntityResultQuery<>(executor, sqlBuilder.get(criteria, type), type);
    }

    @Override
    public <T, R> ProjectionResultQuery<R> getProjectionQuery(CriteriaQuery criteriaQuery,
                                                              Class<T> type,
                                                              Class<R> projectionType) {
        return new ProjectionResultQueryImpl<>(
                executor, sqlBuilder.get(criteriaQuery, type), type, projectionType);
    }

    @Override
    public ArrayResultQuery getObjectsTypeQuery(CriteriaQuery criteria, Class<?> type) {
        return new JdbcArrayResultQuery(executor, sqlBuilder.get(criteria, type), type);
    }

}
