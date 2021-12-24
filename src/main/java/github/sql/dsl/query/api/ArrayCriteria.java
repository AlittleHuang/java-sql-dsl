package github.sql.dsl.query.api;

import github.sql.dsl.query.api.column.EntityColumn;

import java.util.List;

public interface ArrayCriteria<T> extends ArrayResults {

    <U extends Entity> EntityOrderBuyBuilder<ArrayCriteria<T>, U> orderBy(EntityColumn<T, U> column);

    ArrayCriteria<T> groupBy(EntityColumn<T, ?> column);

    ArrayCriteria<T> groupBy(List<EntityColumn<T, ?>> columns);

    ArrayCriteria<T> select(EntityColumn<T, ?> column);

    ArrayCriteria<T> select(List<EntityColumn<T, ?>> columns);

}
