package github.sql.dsl.internal.jdbc.sql;

import github.sql.dsl.criteria.query.builder.ArrayResultQuery;
import github.sql.dsl.criteria.query.builder.EntityResultQuery;
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
    public <T> EntityResultQuery<T> getTypeQuery(CriteriaQuery criteria, Class<T> type) {
        return new JdbcEntityEntityResultQuery<>(executor, sqlBuilder.get(criteria, type), type);
    }

    @Override
    public ArrayResultQuery getObjectsTypeQuery(CriteriaQuery criteria, Class<?> type) {
        return new JdbcArrayResultQuery(executor, sqlBuilder.get(criteria, type), type);
    }

}
