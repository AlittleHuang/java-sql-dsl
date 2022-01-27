package github.sql.dsl.criteria.query.support.builder.query;

import github.sql.dsl.criteria.query.builder.*;
import github.sql.dsl.criteria.query.builder.combination.*;
import github.sql.dsl.criteria.query.support.CriteriaQuery;
import github.sql.dsl.criteria.query.support.TypeQueryFactory;
import github.sql.dsl.criteria.query.support.builder.criteria.*;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractResult<T> {


    protected final TypeQueryFactory typeQueryFactory;
    protected final Class<T> entityType;
    protected final CriteriaQueryImpl criteriaQuery;

    public AbstractResult(TypeQueryFactory typeQueryFactory, Class<T> entityType, CriteriaQuery criteriaQuery) {
        this.typeQueryFactory = typeQueryFactory;
        this.entityType = entityType;
        this.criteriaQuery = CriteriaQueryImpl.from(criteriaQuery);
    }

    protected ResultBuilder<Object[]> getObjectsTypeQuery() {
        return typeQueryFactory.getObjectsTypeQuery(criteriaQuery, entityType);
    }

    protected ResultBuilder<T> getTypeQuery() {
        return typeQueryFactory.getEntityResultQuery(criteriaQuery, entityType);
    }

    @NotNull
    protected Selectable<T, ObjectsResultBuilder<T>> getSelectable() {
        return new SelectableImpl<>(this.criteriaQuery.getSelectionList(), next ->
                new ObjectsResultBuilderImpl<>(this.typeQueryFactory, this.entityType, this.criteriaQuery.updateSelection(next)));
    }

    @NotNull
    protected Groupable<T, GroupByBuilder<T>> getGroupable() {
        return new GroupableImpl<>(criteriaQuery.getGroupList(), next -> new GroupByBuilderImpl<>(
                this.typeQueryFactory,
                this.entityType,
                this.criteriaQuery.updateGroupList(next)));
    }

    @NotNull
    protected Sortable<T, WhereAssembler<T>> getSortable() {
        return new SortableImpl<>(criteriaQuery.getOrderList(),
                next -> whereBuilder(this.criteriaQuery.updateOrderList(next)));
    }

    @NotNull
    protected Fetchable<T, EntityResultBuilder<T>> getFetchable() {
        return new FetchableImpl<>(criteriaQuery.getFetchList(),
                next -> new EntityResultBuilderImpl<>(typeQueryFactory, entityType, criteriaQuery.updateFetch(next)));
    }

    @NotNull
    protected WhereableImpl<T, WhereAssembler<T>> getWhereable() {
        return new WhereableImpl<>(next -> whereBuilder(criteriaQuery.updateRestriction(next)));
    }

    @NotNull
    protected PredicateAssembler<T, EntityResultBuilder<T>> getRestrictionBuilder() {
        return new PredicateAssemblerImpl<>(criteriaQuery.getRestriction(),
                next -> new EntityResultBuilderImpl<>(typeQueryFactory, entityType, criteriaQuery.updateRestriction(next)));
    }


    protected WhereAssembler<T> whereBuilder(CriteriaQuery criteriaQuery) {
        return new WhereAssemblerImpl<>(this.typeQueryFactory, this.entityType, criteriaQuery);
    }

    @NotNull
    protected Whereable<T, ObjectsResultBuilder<T>> getObjectsWhereable() {
        return new WhereableImpl<>(next -> {
            CriteriaQueryImpl updated = this.criteriaQuery.updateRestriction(next);
            return new ObjectsResultBuilderImpl<>(typeQueryFactory, entityType, updated);
        });
    }

    @NotNull
    protected Sortable<T, ObjectsResultBuilder<T>> getObjectsSortable() {
        return new SortableImpl<>(criteriaQuery.getOrderList(), next -> {
            CriteriaQueryImpl updated = this.criteriaQuery.updateOrderList(next);
            return new ObjectsResultBuilderImpl<>(typeQueryFactory, entityType, updated);
        });
    }

    protected @NotNull PredicateAssembler<T, WhereAssembler<T>> getWereBuilderRestrictionBuilder() {
        return new PredicateAssemblerImpl<>(criteriaQuery.getRestriction(), next -> {
            CriteriaQueryImpl updated = this.criteriaQuery.updateRestriction(next);
            return new WhereAssemblerImpl<>(typeQueryFactory, entityType, updated);
        });
    }

    protected @NotNull Sortable<T, EntityResultBuilder<T>> getEntityQuerySortable() {
        return new SortableImpl<>(criteriaQuery.getOrderList(), next -> {
            CriteriaQueryImpl updated = this.criteriaQuery.updateOrderList(next);
            return new EntityResultBuilderImpl<>(typeQueryFactory, entityType, updated);
        });
    }

    protected @NotNull AggregateSelectable<T, AggregateObjectsResultBuilder<T>> getAggregateSelectable() {
        return new AggregateSelectableImpl<>(this.criteriaQuery.getSelectionList(), next ->
                new AggregateObjectsResultBuilderImpl<>(this.typeQueryFactory, this.entityType, this.criteriaQuery.updateSelection(next)));
    }

    public <R> ResultBuilder<R> projected(Class<R> projectionType) {
        return typeQueryFactory.getProjectionQuery(this.criteriaQuery, entityType, projectionType);
    }

}
