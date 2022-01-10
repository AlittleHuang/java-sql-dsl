package github.sql.dsl.query.jdbc.sql;

import github.sql.dsl.query.api.builder.ArrayResultQuery;
import github.sql.dsl.query.api.builder.EntityResultQuery;
import github.sql.dsl.query.api.suport.CriteriaQuery;
import github.sql.dsl.query.api.suport.TypeQueryFactory;

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
