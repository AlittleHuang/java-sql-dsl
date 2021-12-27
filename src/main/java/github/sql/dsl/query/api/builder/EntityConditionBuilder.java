package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.Entity;
import github.sql.dsl.query.api.column.*;

import java.util.Date;

public interface EntityConditionBuilder<T, U> {


    <V extends Entity> EntityConditionBuilder<T, V> to(EntityColumn<U, V> column);

    <V extends Number> NumberConditionBuilder<T, V> to(NumberColumn<U, V> column);

    <V extends Date> ComparableConditionBuilder<T, V> to(DateColumn<U, V> column);

    <V extends Date> ConditionBuilder<T, V> to(Column<U, V> column);

    StringConditionBuilder<T> to(StringColumn<U> column);


}
