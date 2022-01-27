package github.sql.dsl.criteria.query.support.builder.query;

import github.sql.dsl.criteria.query.builder.*;
import github.sql.dsl.criteria.query.builder.combination.*;
import github.sql.dsl.criteria.query.support.CriteriaQuery;
import github.sql.dsl.criteria.query.support.TypeQueryFactory;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;

public class QueryImpl<T> extends AbstractResult<T> implements Query<T> {

    public QueryImpl(TypeQueryFactory typeQueryFactory, Class<T> entityType, CriteriaQuery criteriaQuery) {
        super(typeQueryFactory, entityType, criteriaQuery);
    }

    @Delegate
    @Override
    protected @NotNull WhereableImpl<T, WhereAssembler<T>> getWhereable() {
        return super.getWhereable();
    }

    @Delegate
    @Override
    protected @NotNull Fetchable<T, EntityResultBuilder<T>> getFetchable() {
        return super.getFetchable();
    }

    @Delegate
    @Override
    protected @NotNull Sortable<T, WhereAssembler<T>> getSortable() {
        return super.getSortable();
    }

    @Delegate
    @Override
    protected @NotNull Groupable<T, GroupByBuilder<T>> getGroupable() {
        return super.getGroupable();
    }

    @Delegate
    @Override
    protected @NotNull Selectable<T, ObjectsResultBuilder<T>> getSelectable() {
        return super.getSelectable();
    }

    @Delegate
    @Override
    protected @NotNull AggregateSelectable<T, AggregateObjectsResultBuilder<T>> getAggregateSelectable() {
        return super.getAggregateSelectable();
    }

    @Delegate
    @Override
    protected ResultBuilder<T> getTypeQuery() {
        return super.getTypeQuery();
    }

}