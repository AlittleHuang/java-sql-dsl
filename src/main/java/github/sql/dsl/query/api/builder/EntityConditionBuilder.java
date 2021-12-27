package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.Entity;
import github.sql.dsl.query.api.column.*;

import java.util.Date;

public interface EntityConditionBuilder<T, U, V> {

    <R extends Entity> EntityConditionBuilder<T, R, V> to(EntityColumn<U, R> column);

    <R extends Number> NumberConditionBuilder<T, R, V> to(NumberColumn<U, R> column);

    <R extends Date> ComparableConditionBuilder<T, R, V> to(DateColumn<U, R> column);

    <R extends Date> ConditionBuilder<T, R, V> to(Column<U, R> column);

    StringConditionBuilder<T, V> to(StringColumn<U> column);


}
