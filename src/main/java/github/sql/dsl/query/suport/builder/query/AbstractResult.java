package github.sql.dsl.query.suport.builder.query;

import github.sql.dsl.query.api.query.ObjectsTypeQuery;
import github.sql.dsl.query.api.query.ProjectionResults;
import github.sql.dsl.query.api.query.TypeQuery;
import github.sql.dsl.query.api.builder.*;
import github.sql.dsl.query.api.query.EntityQuery;
import github.sql.dsl.query.api.query.ObjectsQuery;
import github.sql.dsl.query.api.query.WhereBuilder;
import github.sql.dsl.query.api.query.Whereable;
import github.sql.dsl.query.suport.CriteriaQuery;
import github.sql.dsl.query.suport.ResultsFactory;
import github.sql.dsl.query.suport.builder.criteria.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractResult<T> implements TypeQuery<T>, ObjectsTypeQuery {

    protected final ResultsFactory resultsFactory;
    protected final Class<T> entityType;
    protected final CriteriaQueryImpl criteriaQuery;

    public AbstractResult(ResultsFactory resultsFactory, Class<T> entityType, CriteriaQuery criteriaQuery) {
        this.resultsFactory = resultsFactory;
        this.entityType = entityType;
        this.criteriaQuery = CriteriaQueryImpl.from(criteriaQuery);
    }

    protected ObjectsTypeQuery getObjectsTypeQuery() {
        return resultsFactory.arrayResults(criteriaQuery, entityType);
    }

    protected TypeQuery<T> getTypeQuery() {
        return resultsFactory.results(criteriaQuery, entityType);
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

    @Override
    public <U> ProjectionResults<T> projection(Class<U> projectionType) {
        return getTypeQuery().projection(projectionType);
    }

    @NotNull
    protected Selectable<T, ObjectsQuery<T>> getSelectable() {
        return new SelectableImpl<>(this.criteriaQuery.getSelection(), next ->
                new ObjectsQueryImpl<>(this.resultsFactory, this.entityType, this.criteriaQuery.updateSelection(next)));
    }

    @NotNull
    protected Groupable<T, ObjectsQuery<T>> getGroupable() {
        return new GroupableImpl<>(criteriaQuery.getGroupList(), next -> new ObjectsQueryImpl<>(
                this.resultsFactory,
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
        return new FetchableImpl<>(criteriaQuery.getFetch(),
                next -> new EntityQueryImpl<>(resultsFactory, entityType, criteriaQuery.updateFetch(next)));
    }

    @NotNull
    protected WhereableImpl<T, WhereBuilder<T>> getWhereable() {
        return new WhereableImpl<>(next -> whereBuilder(criteriaQuery.updateRestriction(next)));
    }

    @NotNull
    protected PredicateCombinable<T, EntityQuery<T>> getRestrictionBuilder() {
        return new PredicateCombinableImpl<>(criteriaQuery.getRestriction(),
                next -> new EntityQueryImpl<>(resultsFactory, entityType, criteriaQuery.updateRestriction(next)));
    }


    protected WhereBuilder<T> whereBuilder(CriteriaQuery criteriaQuery) {
        return new WhereBuilderImpl<>(this.resultsFactory, this.entityType, criteriaQuery);
    }

    @NotNull
    protected Whereable<T, ObjectsQuery<T>> getObjectsWhereable() {
        return new WhereableImpl<>(next -> {
            CriteriaQueryImpl updated = this.criteriaQuery.updateRestriction(next);
            return new ObjectsQueryImpl<>(resultsFactory, entityType, updated);
        });

    }

    @NotNull
    protected Sortable<T, ObjectsQuery<T>> getObjectsSortable() {
        return new SortableImpl<>(criteriaQuery.getOrderList(), next -> {
            CriteriaQueryImpl updated = this.criteriaQuery.updateOrderList(next);
            return new ObjectsQueryImpl<>(resultsFactory, entityType, updated);
        });
    }

    protected @NotNull PredicateCombinable<T, WhereBuilder<T>> getWereBuilderRestrictionBuilder() {
        return new PredicateCombinableImpl<>(criteriaQuery.getRestriction(), next -> {
            CriteriaQueryImpl updated = this.criteriaQuery.updateRestriction(next);
            return new WhereBuilderImpl<>(resultsFactory, entityType, updated);
        });
    }

    protected @NotNull Sortable<T, EntityQuery<T>> getEntityQuerySortable() {
        return new SortableImpl<>(criteriaQuery.getOrderList(), next -> {
            CriteriaQueryImpl updated = this.criteriaQuery.updateOrderList(next);
            return new EntityQueryImpl<>(resultsFactory, entityType, updated);
        });
    }
}
