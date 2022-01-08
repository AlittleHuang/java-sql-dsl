package github.sql.dsl.query.suport.builder.query;

import github.sql.dsl.query.api.builder.*;
import github.sql.dsl.query.api.query.ArrayQuery;
import github.sql.dsl.query.api.query.EntityQuery;
import github.sql.dsl.query.api.query.WhereBuilder;
import github.sql.dsl.query.suport.CriteriaQuery;
import github.sql.dsl.query.suport.TypeQueryFactory;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;

public class WhereBuilderImpl<T> extends AbstractResult<T> implements WhereBuilder<T> {
    public WhereBuilderImpl(TypeQueryFactory typeQueryFactory, Class<T> entityType, CriteriaQuery criteriaQuery) {
        super(typeQueryFactory, entityType, criteriaQuery);
    }

    @Delegate
    protected @NotNull PredicateCombinable<T, WhereBuilder<T>> getWereBuilderRestrictionBuilder() {
        return super.getWereBuilderRestrictionBuilder();
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


}