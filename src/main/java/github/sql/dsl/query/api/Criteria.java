package github.sql.dsl.query.api;

import github.sql.dsl.query.api.column.EntityColumn;
import github.sql.dsl.query.api.column.NumberColumn;
import github.sql.dsl.query.api.column.StringColumn;

public interface Criteria<T> extends Results<T> {

    <U extends Number> OrderBuyBuilder<Criteria<T>, U> orderBy(NumberColumn<T, U> column);

    OrderBuyBuilder<Criteria<T>, String> orderBy(StringColumn<T> column);

    Selections<T> groupBy(EntityColumn<T, ?> selections);

    ArrayCriteria<T> select(EntityColumn<T, ?> selections);

    FetchBuilder<T> fetch(EntityColumn<T, ?> column);

}
