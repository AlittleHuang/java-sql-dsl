package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.Entity;
import github.sql.dsl.query.api.column.*;

import java.util.Date;

public interface WhereClausesBuilder<T, V> {

    <U extends Entity> EntityConditionBuilder<T, U, V> and(EntityColumn<T, U> column);

    <U extends Entity> EntityConditionBuilder<T, U, V> or(EntityColumn<T, U> column);

    <U extends Entity> EntityConditionBuilder<T, U, V> andNot(EntityColumn<T, U> column);

    <U extends Entity> EntityConditionBuilder<T, U, V> orNot(EntityColumn<T, U> column);

    <U> ConditionBuilder<T, U, V> and(Column<T, U> column);

    <U> ConditionBuilder<T, U, V> or(Column<T, U> column);

    <U> ConditionBuilder<T, U, V> andNot(Column<T, U> column);

    <U> ConditionBuilder<T, U, V> orNot(Column<T, U> column);

    <U extends Number> NumberConditionBuilder<T, U, V> and(NumberColumn<T, U> column);

    <U extends Number> NumberConditionBuilder<T, U, V> or(NumberColumn<T, U> column);

    <U extends Number> NumberConditionBuilder<T, U, V> andNot(NumberColumn<T, U> column);

    <U extends Number> NumberConditionBuilder<T, U, V> orNot(NumberColumn<T, U> column);

    <U extends Date> ComparableConditionBuilder<T, U, V> and(DateColumn<T, U> column);

    <U extends Date> ComparableConditionBuilder<T, U, V> or(DateColumn<T, U> column);

    <U extends Date> ComparableConditionBuilder<T, U, V> andNot(DateColumn<T, U> column);

    <U extends Date> ComparableConditionBuilder<T, U, V> orNot(DateColumn<T, U> column);

    StringConditionBuilder<T, V> and(StringColumn<T> column);

    StringConditionBuilder<T, V> or(StringColumn<T> column);

    StringConditionBuilder<T, V> andNot(StringColumn<T> column);

    StringConditionBuilder<T, V> orNot(StringColumn<T> column);

}
