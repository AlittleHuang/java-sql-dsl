package github.sql.dsl.query.suport.jdbc.sql;

import github.sql.dsl.query.api.ObjectsTypeQuery;
import github.sql.dsl.query.api.TypeQuery;
import github.sql.dsl.query.suport.common.ResultsFactory;
import github.sql.dsl.query.suport.common.model.CriteriaQuery;

public class JdbcQueryResultsFactory implements ResultsFactory {
    private final PreparedSqlExecutor executor;
    private final SqlBuilderFactory sqlBuilder;

    public JdbcQueryResultsFactory(PreparedSqlExecutor executor,
                                   SqlBuilderFactory sqlBuilder) {
        this.executor = executor;
        this.sqlBuilder = sqlBuilder;
    }

    @Override
    public <T> TypeQuery<T> results(CriteriaQuery criteria, Class<T> type) {
        return new JdbcEntityTypeQuery<>(executor, sqlBuilder.get(criteria, type), type);
    }

    @Override
    public ObjectsTypeQuery arrayResults(CriteriaQuery criteria, Class<?> type) {
        return new JdbcObjectsTypeQuery(executor, sqlBuilder.get(criteria, type), type);
    }

}
