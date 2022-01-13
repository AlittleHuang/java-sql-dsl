package github.sql.dsl.criteria.query.support.builder.query;

import github.sql.dsl.criteria.query.builder.AggregateSelectable;
import github.sql.dsl.criteria.query.builder.Groupable;
import github.sql.dsl.criteria.query.builder.Sortable;
import github.sql.dsl.criteria.query.builder.TypeResultQuery;
import github.sql.dsl.criteria.query.builder.combination.AggregateObjectsQuery;
import github.sql.dsl.criteria.query.builder.combination.ArrayQuery;
import github.sql.dsl.criteria.query.builder.combination.Whereable;
import github.sql.dsl.criteria.query.support.CriteriaQuery;
import github.sql.dsl.criteria.query.support.TypeQueryFactory;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;

public class AggregateObjectsQueryImpl<T> extends AbstractResult<T> implements AggregateObjectsQuery<T> {

    public AggregateObjectsQueryImpl(TypeQueryFactory typeQueryFactory, Class<T> entityType, CriteriaQuery criteriaQuery) {
        super(typeQueryFactory, entityType, criteriaQuery);
    }

    @Delegate
    @Override
    protected @NotNull Whereable<T, ArrayQuery<T>> getObjectsWhereable() {
        return super.getObjectsWhereable();
    }

    @Delegate
    @Override
    protected @NotNull Sortable<T, ArrayQuery<T>> getObjectsSortable() {
        return super.getObjectsSortable();
    }

    @Delegate
    @Override
    protected @NotNull Groupable<T, ArrayQuery<T>> getGroupable() {
        return super.getGroupable();
    }

    @Delegate
    @Override
    protected @NotNull AggregateSelectable<T, AggregateObjectsQuery<T>> getAggregateSelectable() {
        return super.getAggregateSelectable();
    }

    @Delegate
    @Override
    protected TypeResultQuery<Object[]> getObjectsTypeQuery() {
        return super.getObjectsTypeQuery();
    }
}
