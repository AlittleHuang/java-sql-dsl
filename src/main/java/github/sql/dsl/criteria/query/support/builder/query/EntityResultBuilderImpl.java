package github.sql.dsl.criteria.query.support.builder.query;

import github.sql.dsl.criteria.query.builder.Fetchable;
import github.sql.dsl.criteria.query.builder.PredicateAssembler;
import github.sql.dsl.criteria.query.builder.ResultBuilder;
import github.sql.dsl.criteria.query.builder.Sortable;
import github.sql.dsl.criteria.query.builder.combination.EntityResultBuilder;
import github.sql.dsl.criteria.query.support.CriteriaQuery;
import github.sql.dsl.criteria.query.support.TypeQueryFactory;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;

public class EntityResultBuilderImpl<T> extends AbstractResult<T> implements EntityResultBuilder<T> {

    public EntityResultBuilderImpl(TypeQueryFactory typeQueryFactory, Class<T> entityType, CriteriaQuery criteriaQuery) {
        super(typeQueryFactory, entityType, criteriaQuery);
    }

    @Delegate
    @Override
    protected @NotNull PredicateAssembler<T, EntityResultBuilder<T>> getRestrictionBuilder() {
        return super.getRestrictionBuilder();
    }

    @Delegate
    @Override
    protected @NotNull Fetchable<T, EntityResultBuilder<T>> getFetchable() {
        return super.getFetchable();
    }

    @Delegate
    protected @NotNull Sortable<T, EntityResultBuilder<T>> getEntityQuerySortable() {
        return super.getEntityQuerySortable();
    }


    @Delegate
    @Override
    protected ResultBuilder<T> getTypeQuery() {
        return super.getTypeQuery();
    }
}
