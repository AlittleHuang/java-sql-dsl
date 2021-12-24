package github.sql.dsl.query.api;

import github.sql.dsl.query.api.column.*;

import java.util.Date;

public interface EntityOrderBuyBuilder<T, U> {

    <V extends Entity> EntityOrderBuyBuilder<T, V> to(EntityColumn<U, V> column);

    <V extends Date> OrderBuyBuilder<T, V> to(DateColumn<U, V> column);

    <V extends Number> OrderBuyBuilder<T, V> to(NumberColumn<U, V> column);

    OrderBuyBuilder<T, String> to(StringColumn<T> column);

    OrderBuyBuilder<T, String> to(BooleanColumn<T> column);


}
