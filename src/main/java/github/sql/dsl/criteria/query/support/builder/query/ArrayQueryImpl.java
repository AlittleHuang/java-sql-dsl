package github.sql.dsl.criteria.query.support.builder.query;

import github.sql.dsl.criteria.query.builder.Selectable;
import github.sql.dsl.criteria.query.builder.Sortable;
import github.sql.dsl.criteria.query.builder.TypeResultQuery;
import github.sql.dsl.criteria.query.builder.combination.ArrayQuery;
import github.sql.dsl.criteria.query.builder.combination.Whereable;
import github.sql.dsl.criteria.query.support.CriteriaQuery;
import github.sql.dsl.criteria.query.support.TypeQueryFactory;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;

public class ArrayQueryImpl<T> extends AbstractResult<T> implements ArrayQuery<T> {

    public ArrayQueryImpl(TypeQueryFactory typeQueryFactory, Class<T> entityType, CriteriaQuery criteriaQuery) {
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
    protected @NotNull Selectable<T, ArrayQuery<T>> getSelectable() {
        return super.getSelectable();
    }

    @Delegate
    @Override
    protected TypeResultQuery<Object[]> getObjectsTypeQuery() {
        return super.getObjectsTypeQuery();
    }

}