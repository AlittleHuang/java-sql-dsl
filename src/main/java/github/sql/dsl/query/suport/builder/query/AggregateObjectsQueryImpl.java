package github.sql.dsl.query.suport.builder.query;

import github.sql.dsl.query.api.builder.AggregateSelectable;
import github.sql.dsl.query.api.builder.Groupable;
import github.sql.dsl.query.api.builder.Sortable;
import github.sql.dsl.query.api.query.AggregateObjectsQuery;
import github.sql.dsl.query.api.query.ObjectsQuery;
import github.sql.dsl.query.api.query.Whereable;
import github.sql.dsl.query.suport.CriteriaQuery;
import github.sql.dsl.query.suport.TypeQueryFactory;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;

public class AggregateObjectsQueryImpl<T> extends AbstractResult<T> implements AggregateObjectsQuery<T> {

    public AggregateObjectsQueryImpl(TypeQueryFactory typeQueryFactory, Class<T> entityType, CriteriaQuery criteriaQuery) {
        super(typeQueryFactory, entityType, criteriaQuery);
    }

    @Delegate
    @Override
    protected @NotNull Whereable<T, ObjectsQuery<T>> getObjectsWhereable() {
        return super.getObjectsWhereable();
    }

    @Delegate
    @Override
    protected @NotNull Sortable<T, ObjectsQuery<T>> getObjectsSortable() {
        return super.getObjectsSortable();
    }

    @Delegate
    @Override
    protected @NotNull Groupable<T, ObjectsQuery<T>> getGroupable() {
        return super.getGroupable();
    }

    @Delegate
    @Override
    protected @NotNull AggregateSelectable<T, AggregateObjectsQuery<T>> getAggregateSelectable() {
        return super.getAggregateSelectable();
    }

}
