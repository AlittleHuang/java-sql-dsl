package github.sql.dsl.query.api;

import github.sql.dsl.query.api.builder.*;
import github.sql.dsl.query.api.column.*;
import github.sql.dsl.query.api.expression.BooleanExpression;

import java.util.Date;
import java.util.function.Function;

public interface QueryBuilder<T> extends Criteria<T> {

    <U extends Entity> EntityConditionBuilder<T, U, WhereClauses<T>> where(EntityColumn<T, U> column);

    <U> ConditionBuilder<T, U, WhereClauses<T>> where(Column<T, U> column);

    <U extends Number> NumberConditionBuilder<T, U, WhereClauses<T>> where(NumberColumn<T, U> column);

    <U extends Date> ComparableConditionBuilder<T, U, WhereClauses<T>> where(DateColumn<T, U> column);

    StringConditionBuilder<T, WhereClauses<T>> where(StringColumn<T> column);

    <U extends Entity> EntityConditionBuilder<T, U, WhereClauses<T>> whereNot(EntityColumn<T, U> column);

    <U> ConditionBuilder<T, U, WhereClauses<T>> whereNot(Column<T, U> column);

    <U extends Number> NumberConditionBuilder<T, U, WhereClauses<T>> whereNot(NumberColumn<T, U> column);

    <U extends Date> ComparableConditionBuilder<T, U, WhereClauses<T>> whereNot(DateColumn<T, U> column);

    StringConditionBuilder<T, WhereClauses<T>> whereNot(StringColumn<T> column);

    WhereClauses<T> wheres(Function<WhereClauses.Builder<T>, BooleanExpression> column);

}
