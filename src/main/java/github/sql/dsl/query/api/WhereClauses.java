package github.sql.dsl.query.api;

import github.sql.dsl.query.api.column.*;
import github.sql.dsl.query.api.condition.*;

import java.util.Date;
import java.util.function.Consumer;

public interface WhereClauses<T> extends Results<T>, Criteria<T> {

    WhereClauses<T> and(Consumer<WhereClauses<T>> builder);

    WhereClauses<T> or(Consumer<WhereClauses<T>> builder);

    WhereClauses<T> andNot(Consumer<WhereClauses<T>> builder);

    WhereClauses<T> orNot(Consumer<WhereClauses<T>> builder);

    <U extends Entity> EntityConditionBuilder<T, U> and(EntityColumn<T, U> column);

    <U extends Entity> EntityConditionBuilder<T, U> or(EntityColumn<T, U> column);

    <U extends Entity> EntityConditionBuilder<T, U> andNot(EntityColumn<T, U> column);

    <U extends Entity> EntityConditionBuilder<T, U> orNot(EntityColumn<T, U> column);

    <U> ConditionBuilder<T, U> and(AnyColumn<T, U> column);

    <U> ConditionBuilder<T, U> or(AnyColumn<T, U> column);

    <U> ConditionBuilder<T, U> andNot(AnyColumn<T, U> column);

    <U> ConditionBuilder<T, U> orNot(AnyColumn<T, U> column);

    <U extends Number> NumberConditionBuilder<T, U> and(NumberColumn<T, U> column);

    <U extends Number> NumberConditionBuilder<T, U> or(NumberColumn<T, U> column);

    <U extends Number> NumberConditionBuilder<T, U> andNot(NumberColumn<T, U> column);

    <U extends Number> NumberConditionBuilder<T, U> orNot(NumberColumn<T, U> column);

    <U extends Date> ComparableConditionBuilder<T, U> and(DateColumn<T, U> column);

    <U extends Date> ComparableConditionBuilder<T, U> or(DateColumn<T, U> column);

    <U extends Date> ComparableConditionBuilder<T, U> andNot(DateColumn<T, U> column);

    <U extends Date> ComparableConditionBuilder<T, U> orNot(DateColumn<T, U> column);

    StringConditionBuilder<T> and(StringColumn<T> column);

    StringConditionBuilder<T> or(StringColumn<T> column);

    StringConditionBuilder<T> andNot(StringColumn<T> column);

    StringConditionBuilder<T> orNot(StringColumn<T> column);

    StringConditionBuilder<T> andNot(BooleanColumn<T> column);

    StringConditionBuilder<T> orNot(BooleanColumn<T> column);

}
