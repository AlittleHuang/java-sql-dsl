package github.sql.dsl.query.suport.builder.query;

import github.sql.dsl.query.api.builder.Groupable;
import github.sql.dsl.query.api.builder.Selectable;
import github.sql.dsl.query.api.builder.Sortable;
import github.sql.dsl.query.api.query.ArrayQuery;
import github.sql.dsl.query.api.query.Whereable;
import github.sql.dsl.query.suport.CriteriaQuery;
import github.sql.dsl.query.suport.TypeQueryFactory;
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
    protected @NotNull Groupable<T, ArrayQuery<T>> getGroupable() {
        return super.getGroupable();
    }

    @Delegate
    @Override
    protected @NotNull Selectable<T, ArrayQuery<T>> getSelectable() {
        return super.getSelectable();
    }


}