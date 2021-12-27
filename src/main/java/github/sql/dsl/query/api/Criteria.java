package github.sql.dsl.query.api;

import github.sql.dsl.query.api.column.*;
import github.sql.dsl.query.api.sort.OrderBuyBuilder;

import java.util.Date;
import java.util.List;

public interface Criteria<T> extends Results<T> {

    <U extends Number> OrderBuyBuilder<Criteria<T>> orderBy(NumberColumn<T, U> column);

    <U extends Date> OrderBuyBuilder<Criteria<T>> orderBy(DateColumn<T, U> column);

    OrderBuyBuilder<Criteria<T>> orderBy(StringColumn<T> column);

    ArrayCriteria<T> groupBy(Column<T, ?> selections);

    ArrayCriteria<T> groupBy(List<Column<T, ?>> columns);

    Selections<T> select(Column<T, ?> selection);

    Selections<T> select(List<Column<T, ?>> columns);

    FetchBuilder<T> fetch(EntityColumn<T, ?> column);

}
