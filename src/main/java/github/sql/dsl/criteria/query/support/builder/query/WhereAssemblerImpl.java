package github.sql.dsl.criteria.query.support.builder.query;

import github.sql.dsl.criteria.query.builder.*;
import github.sql.dsl.criteria.query.builder.combination.EntityResultBuilder;
import github.sql.dsl.criteria.query.builder.combination.GroupByBuilder;
import github.sql.dsl.criteria.query.builder.combination.ObjectsResultBuilder;
import github.sql.dsl.criteria.query.builder.combination.WhereAssembler;
import github.sql.dsl.criteria.query.support.SqlCriteriaQuery;
import github.sql.dsl.criteria.query.support.TypeQueryFactory;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;

public class WhereAssemblerImpl<T> extends AbstractResult<T> implements WhereAssembler<T> {
    public WhereAssemblerImpl(TypeQueryFactory typeQueryFactory, Class<T> entityType, SqlCriteriaQuery criteriaQuery) {
        super(typeQueryFactory, entityType, criteriaQuery);
    }

    @Delegate
    protected @NotNull PredicateAssembler<T, WhereAssembler<T>> getWereBuilderRestrictionBuilder() {
        return super.getWereBuilderRestrictionBuilder();
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
    protected ResultBuilder<T> getTypeQuery() {
        return super.getTypeQuery();
    }

}
