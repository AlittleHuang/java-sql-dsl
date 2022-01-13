package github.sql.dsl.criteria.query.support.builder.query;

import github.sql.dsl.criteria.query.builder.Fetchable;
import github.sql.dsl.criteria.query.builder.PredicateCombinable;
import github.sql.dsl.criteria.query.builder.Sortable;
import github.sql.dsl.criteria.query.builder.TypeResultQuery;
import github.sql.dsl.criteria.query.builder.combination.EntityQuery;
import github.sql.dsl.criteria.query.support.CriteriaQuery;
import github.sql.dsl.criteria.query.support.TypeQueryFactory;
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


    @Delegate
    @Override
    protected TypeResultQuery<T> getTypeQuery() {
        return super.getTypeQuery();
    }
}
