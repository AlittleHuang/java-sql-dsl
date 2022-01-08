package github.sql.dsl.query.suport.builder.query;

import github.sql.dsl.query.api.Query;
import github.sql.dsl.query.api.builder.*;
import github.sql.dsl.query.api.builder.combination.AggregateObjectsQuery;
import github.sql.dsl.query.api.builder.combination.ArrayQuery;
import github.sql.dsl.query.api.builder.combination.EntityQuery;
import github.sql.dsl.query.api.builder.combination.WhereBuilder;
import github.sql.dsl.query.suport.CriteriaQuery;
import github.sql.dsl.query.suport.TypeQueryFactory;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;

public class QueryImpl<T> extends AbstractResult<T> implements Query<T> {

    public QueryImpl(TypeQueryFactory typeQueryFactory, Class<T> entityType, CriteriaQuery criteriaQuery) {
        super(typeQueryFactory, entityType, criteriaQuery);
    }

    @Delegate
    @Override
    protected @NotNull WhereableImpl<T, WhereBuilder<T>> getWhereable() {
        return super.getWhereable();
    }

    @Delegate
    @Override
    protected @NotNull Fetchable<T, EntityQuery<T>> getFetchable() {
        return super.getFetchable();
    }

    @Delegate
    @Override
    protected @NotNull Sortable<T, WhereBuilder<T>> getSortable() {
        return super.getSortable();
    }

    @Delegate
    @Override
    protected @NotNull Groupable<T, ArrayQuery<T>> getGroupable() {
        return super.getGroupable();
    }

    @Delegate
    @Override
    protected @NotNull Selectable<T, ArrayQuery<T>> getSelectable() {
        return super.getSelectable();
    }

    @Delegate
    @Override
    protected @NotNull AggregateSelectable<T, AggregateObjectsQuery<T>> getAggregateSelectable() {
        return super.getAggregateSelectable();
    }

}