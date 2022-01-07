package github.sql.dsl.query.suport.builder.query;

import github.sql.dsl.query.api.builder.Fetchable;
import github.sql.dsl.query.api.builder.PredicateCombinable;
import github.sql.dsl.query.api.builder.Sortable;
import github.sql.dsl.query.api.query.EntityQuery;
import github.sql.dsl.query.suport.CriteriaQuery;
import github.sql.dsl.query.suport.TypeQueryFactory;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;

public class EntityQueryImpl<T> extends AbstractResult<T> implements EntityQuery<T> {

    public EntityQueryImpl(TypeQueryFactory typeQueryFactory, Class<T> entityType, CriteriaQuery criteriaQuery) {
        super(typeQueryFactory, entityType, criteriaQuery);
    }

    @Delegate
    @Override
    protected @NotNull PredicateCombinable<T, EntityQuery<T>> getRestrictionBuilder() {
        return super.getRestrictionBuilder();
    }

    @Delegate
    @Override
    protected @NotNull Fetchable<T, EntityQuery<T>> getFetchable() {
        return super.getFetchable();
    }

    //        Sortable<T, EntityQuery<T>>,
    @Delegate
    protected @NotNull Sortable<T, EntityQuery<T>> getEntityQuerySortable() {
        return super.getEntityQuerySortable();
    }

}
