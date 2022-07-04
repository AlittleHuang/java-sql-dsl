package github.sql.dsl.criteria.query.support.builder.query;

import github.sql.dsl.criteria.query.builder.ResultBuilder;
import github.sql.dsl.criteria.query.builder.Selectable;
import github.sql.dsl.criteria.query.builder.Sortable;
import github.sql.dsl.criteria.query.builder.combination.ObjectsResultBuilder;
import github.sql.dsl.criteria.query.builder.combination.Whereable;
import github.sql.dsl.criteria.query.support.SqlCriteriaQuery;
import github.sql.dsl.criteria.query.support.TypeQueryFactory;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;

public class ObjectsResultBuilderImpl<T> extends AbstractResult<T> implements ObjectsResultBuilder<T> {

    public ObjectsResultBuilderImpl(TypeQueryFactory typeQueryFactory, Class<T> entityType, SqlCriteriaQuery criteriaQuery) {
        super(typeQueryFactory, entityType, criteriaQuery);
    }

    @Delegate
    @Override
    protected @NotNull Whereable<T, ObjectsResultBuilder<T>> getObjectsWhereable() {
        return super.getObjectsWhereable();
    }

    @Delegate
    @Override
    protected @NotNull Sortable<T, ObjectsResultBuilder<T>> getObjectsSortable() {
        return super.getObjectsSortable();
    }

    @Delegate
    @Override
    protected @NotNull Selectable<T, ObjectsResultBuilder<T>> getSelectable() {
        return super.getSelectable();
    }

    @Delegate
    @Override
    protected ResultBuilder<Object[]> getObjectsTypeQuery() {
        return super.getObjectsTypeQuery();
    }

}