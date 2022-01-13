package github.sql.dsl.criteria.query.support.builder.query;

import github.sql.dsl.criteria.query.QueryBuilder;
import github.sql.dsl.criteria.query.builder.Query;
import github.sql.dsl.criteria.query.support.TypeQueryFactory;

public abstract class AbstractQueryBuilder implements QueryBuilder {

    private final TypeQueryFactory typeQueryFactory;

    public AbstractQueryBuilder(TypeQueryFactory typeQueryFactory) {
        this.typeQueryFactory = typeQueryFactory;
    }

    @Override
    public <T> Query<T> query(Class<T> type) {
        return new QueryImpl<>(typeQueryFactory, type, null);
    }

}
