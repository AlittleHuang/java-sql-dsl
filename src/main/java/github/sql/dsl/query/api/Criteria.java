package github.sql.dsl.query.api;

import github.sql.dsl.query.api.column.*;

import java.util.Date;
import java.util.List;

public interface Criteria<T> extends Results<T> {

    <U extends Number> OrderBuyBuilder<Criteria<T>, U> orderBy(NumberColumn<T, U> column);

    <U extends Date> OrderBuyBuilder<Criteria<T>, String> orderBy(DateColumn<T, U> column);

    OrderBuyBuilder<Criteria<T>, String> orderBy(StringColumn<T> column);

    Selections<T> groupBy(AnyColumn<T, ?> selections);

    ArrayCriteria<T> groupBy(List<AnyColumn<T, ?>> columns);

    ArrayCriteria<T> select(AnyColumn<T, ?> selections);

    ArrayCriteria<T> select(List<AnyColumn<T, ?>> columns);

    FetchBuilder<T> fetch(EntityColumn<T, ?> column);

}
