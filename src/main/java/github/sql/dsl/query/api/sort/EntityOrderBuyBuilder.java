package github.sql.dsl.query.api.sort;

import github.sql.dsl.query.api.Entity;
import github.sql.dsl.query.api.column.*;

import java.util.Date;

public interface EntityOrderBuyBuilder<T, U> {

    <V extends Entity> EntityOrderBuyBuilder<T, V> to(EntityColumn<U, V> column);

    <V extends Date> OrderBuyBuilder<T> to(DateColumn<U, V> column);

    <V extends Number> OrderBuyBuilder<T> to(NumberColumn<U, V> column);

    OrderBuyBuilder<T> to(StringColumn<T> column);

    OrderBuyBuilder<T> to(BooleanColumn<T> column);


}
