package github.sql.dsl.query.suport.jdbc.sql;

import github.sql.dsl.query.api.query.ObjectsTypeQuery;
import github.sql.dsl.query.api.query.TypeQuery;
import github.sql.dsl.query.suport.CriteriaQuery;
import github.sql.dsl.query.suport.TypeQueryFactory;

public class JdbcQueryTypeQueryFactory implements TypeQueryFactory {
    private final PreparedSqlExecutor executor;
    private final SqlBuilderFactory sqlBuilder;

    public JdbcQueryTypeQueryFactory(PreparedSqlExecutor executor,
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
