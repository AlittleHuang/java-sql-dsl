package github.sql.dsl.query.suport.builder.query;

import github.sql.dsl.query.api.builder.*;
import github.sql.dsl.query.api.query.*;
import github.sql.dsl.query.suport.CriteriaQuery;
import github.sql.dsl.query.suport.TypeQueryFactory;
import github.sql.dsl.query.suport.builder.criteria.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractResult<T> implements TypeQuery<T>, ObjectsTypeQuery {

    protected final TypeQueryFactory typeQueryFactory;
    protected final Class<T> entityType;
    protected final CriteriaQueryImpl criteriaQuery;

    public AbstractResult(TypeQueryFactory typeQueryFactory, Class<T> entityType, CriteriaQuery criteriaQuery) {
        this.typeQueryFactory = typeQueryFactory;
        this.entityType = entityType;
        this.criteriaQuery = CriteriaQueryImpl.from(criteriaQuery);
    }

    protected ObjectsTypeQuery getObjectsTypeQuery() {
        return typeQueryFactory.getObjectsTypeQuery(criteriaQuery, entityType);
    }

    protected TypeQuery<T> getTypeQuery() {
        return typeQueryFactory.getTypeQuery(criteriaQuery, entityType);
    }

    @Override
    public List<Object[]> getObjectsList(int offset, int maxResult) {
        return getObjectsTypeQuery().getObjectsList(offset, maxResult);
    }

    @Override
    public int count() {
        return getTypeQuery().count();
    }

    @Override
    public List<T> getResultList(int offset, int maxResul) {
        return getTypeQuery().getResultList(offset, maxResul);
    }

    @Override
    public boolean exist(int offset) {
        return getTypeQuery().exist(offset);
    }

    @NotNull
    protected Selectable<T, ObjectsQuery<T>> getSelectable() {
        return new SelectableImpl<>(this.criteriaQuery.getSelectionList(), next ->
                new ObjectsQueryImpl<>(this.typeQueryFactory, this.entityType, this.criteriaQuery.updateSelection(next)));
    }

    @NotNull
    protected Groupable<T, ObjectsQuery<T>> getGroupable() {
        return new GroupableImpl<>(criteriaQuery.getGroupList(), next -> new ObjectsQueryImpl<>(
                this.typeQueryFactory,
                this.entityType,
                this.criteriaQuery.updateGroupList(next)));
    }

    @NotNull
    protected Sortable<T, WhereBuilder<T>> getSortable() {
        return new SortableImpl<>(criteriaQuery.getOrderList(),
                next -> whereBuilder(this.criteriaQuery.updateOrderList(next)));
    }

    @NotNull
    protected Fetchable<T, EntityQuery<T>> getFetchable() {
        return new FetchableImpl<>(criteriaQuery.getFetchList(),
                next -> new EntityQueryImpl<>(typeQueryFactory, entityType, criteriaQuery.updateFetch(next)));
    }

    @NotNull
    protected WhereableImpl<T, WhereBuilder<T>> getWhereable() {
        return new WhereableImpl<>(next -> whereBuilder(criteriaQuery.updateRestriction(next)));
    }

    @NotNull
    protected PredicateCombinable<T, EntityQuery<T>> getRestrictionBuilder() {
        return new PredicateCombinableImpl<>(criteriaQuery.getRestriction(),
                next -> new EntityQueryImpl<>(typeQueryFactory, entityType, criteriaQuery.updateRestriction(next)));
    }


    protected WhereBuilder<T> whereBuilder(CriteriaQuery criteriaQuery) {
        return new WhereBuilderImpl<>(this.typeQueryFactory, this.entityType, criteriaQuery);
    }

    @NotNull
    protected Whereable<T, ObjectsQuery<T>> getObjectsWhereable() {
        return new WhereableImpl<>(next -> {
            CriteriaQueryImpl updated = this.criteriaQuery.updateRestriction(next);
            return new ObjectsQueryImpl<>(typeQueryFactory, entityType, updated);
        });

    }

    @NotNull
    protected Sortable<T, ObjectsQuery<T>> getObjectsSortable() {
        return new SortableImpl<>(criteriaQuery.getOrderList(), next -> {
            CriteriaQueryImpl updated = this.criteriaQuery.updateOrderList(next);
            return new ObjectsQueryImpl<>(typeQueryFactory, entityType, updated);
        });
    }

    protected @NotNull PredicateCombinable<T, WhereBuilder<T>> getWereBuilderRestrictionBuilder() {
        return new PredicateCombinableImpl<>(criteriaQuery.getRestriction(), next -> {
            CriteriaQueryImpl updated = this.criteriaQuery.updateRestriction(next);
            return new WhereBuilderImpl<>(typeQueryFactory, entityType, updated);
        });
    }

    protected @NotNull Sortable<T, EntityQuery<T>> getEntityQuerySortable() {
        return new SortableImpl<>(criteriaQuery.getOrderList(), next -> {
            CriteriaQueryImpl updated = this.criteriaQuery.updateOrderList(next);
            return new EntityQueryImpl<>(typeQueryFactory, entityType, updated);
        });
    }

    protected @NotNull AggregateSelectable<T, AggregateObjectsQuery<T>> getAggregateSelectable() {
        return new AggregateSelectableImpl<>(this.criteriaQuery.getSelectionList(), next ->
                new AggregateObjectsQueryImpl<>(this.typeQueryFactory, this.entityType, this.criteriaQuery.updateSelection(next)));
    }


}
