package github.sql.dsl.internal.jdbc.sql;

import github.sql.dsl.criteria.query.builder.ResultBuilder;
import github.sql.dsl.criteria.query.support.CriteriaQuery;
import github.sql.dsl.criteria.query.support.TypeQueryFactory;

public class JdbcQueryTypeQueryFactory implements TypeQueryFactory {
    private final PreparedSqlExecutor executor;
    private final SqlBuilderFactory sqlBuilder;

    public JdbcQueryTypeQueryFactory(PreparedSqlExecutor executor,
                                     SqlBuilderFactory sqlBuilder) {
        this.executor = executor;
        this.sqlBuilder = sqlBuilder;
    }

    @Override
    public <T> ResultBuilder<T> getEntityResultQuery(CriteriaQuery criteria, Class<T> type) {
        return new JdbcEntityResultBuilder<>(executor, sqlBuilder.get(criteria, type), type);
    }

    @Override
    public ResultBuilder<Object[]> getObjectsTypeQuery(CriteriaQuery criteria, Class<?> type) {
        return new JdbcObjectsResultBuilder(executor, sqlBuilder.get(criteria, type), type);
    }

}
