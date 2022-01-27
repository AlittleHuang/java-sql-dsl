package github.sql.dsl.criteria.query.support.builder.query;

import github.sql.dsl.criteria.query.builder.AggregateSelectable;
import github.sql.dsl.criteria.query.builder.Groupable;
import github.sql.dsl.criteria.query.builder.Selectable;
import github.sql.dsl.criteria.query.builder.combination.AggregateObjectsResultBuilder;
import github.sql.dsl.criteria.query.builder.combination.GroupByBuilder;
import github.sql.dsl.criteria.query.builder.combination.ObjectsResultBuilder;
import github.sql.dsl.criteria.query.support.CriteriaQuery;
import github.sql.dsl.criteria.query.support.TypeQueryFactory;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;

public class GroupByBuilderImpl<T> extends AbstractResult<T> implements GroupByBuilder<T> {

    public GroupByBuilderImpl(TypeQueryFactory typeQueryFactory,
                              Class<T> entityType,
                              CriteriaQuery criteriaQuery) {
        super(typeQueryFactory, entityType, criteriaQuery);
    }

    @Delegate
    @Override
    protected @NotNull Selectable<T, ObjectsResultBuilder<T>> getSelectable() {
        return super.getSelectable();
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
}
