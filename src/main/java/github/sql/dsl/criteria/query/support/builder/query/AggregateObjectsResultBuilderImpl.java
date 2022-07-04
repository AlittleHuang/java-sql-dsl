package github.sql.dsl.criteria.query.support.builder.query;

import github.sql.dsl.criteria.query.builder.AggregateSelectable;
import github.sql.dsl.criteria.query.builder.Groupable;
import github.sql.dsl.criteria.query.builder.ResultBuilder;
import github.sql.dsl.criteria.query.builder.combination.AggregateObjectsResultBuilder;
import github.sql.dsl.criteria.query.builder.combination.GroupByBuilder;
import github.sql.dsl.criteria.query.builder.combination.ObjectsResultBuilder;
import github.sql.dsl.criteria.query.builder.combination.Whereable;
import github.sql.dsl.criteria.query.support.SqlCriteriaQuery;
import github.sql.dsl.criteria.query.support.TypeQueryFactory;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;

public class AggregateObjectsResultBuilderImpl<T> extends AbstractResult<T> implements AggregateObjectsResultBuilder<T> {

    public AggregateObjectsResultBuilderImpl(TypeQueryFactory typeQueryFactory, Class<T> entityType, SqlCriteriaQuery criteriaQuery) {
        super(typeQueryFactory, entityType, criteriaQuery);
    }

    @Delegate
    @Override
    protected @NotNull Whereable<T, ObjectsResultBuilder<T>> getObjectsWhereable() {
        return super.getObjectsWhereable();
    }

    @Delegate
    @Override
    protected @NotNull Groupable<T, GroupByBuilder<T>> getGroupable() {
        return super.getGroupable();
    }

    @Delegate
    @Override
    protected @NotNull AggregateSelectable<T, AggregateObjectsResultBuilder<T>> getAggregateSelectable() {
        return super.getAggregateSelectable();
    }

    @Delegate
    @Override
    protected ResultBuilder<Object[]> getObjectsTypeQuery() {
        return super.getObjectsTypeQuery();
    }
}
