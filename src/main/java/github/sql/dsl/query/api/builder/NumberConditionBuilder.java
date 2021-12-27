package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.WhereClauses;
import github.sql.dsl.query.api.column.NumberColumn;
import github.sql.dsl.query.suport.common.expression.Expression;

public interface NumberConditionBuilder<T, U extends Number> extends ConditionBuilder<T,U> {

    NumberConditionBuilder<T, U> add(U v);

    NumberConditionBuilder<T, U> subtract(U v);

    NumberConditionBuilder<T, U> multiply(U v);

    NumberConditionBuilder<T, U> divide(U v);

    NumberConditionBuilder<T, U> mod(U v);

    @Override
    NumberConditionBuilder<T, U> nullIf(U value);

    WhereClauses<T> ge(U value);

    WhereClauses<T> gt(U value);

    WhereClauses<T> le(U value);

    WhereClauses<T> between(U a, U b);

    WhereClauses<T> lt(U value);


    NumberConditionBuilder<T, U> add(NumberColumn<T,U> v);

    NumberConditionBuilder<T, U> subtract(NumberColumn<T,U> v);

    NumberConditionBuilder<T, U> multiply(NumberColumn<T,U> v);

    NumberConditionBuilder<T, U> divide(NumberColumn<T,U> v);

    NumberConditionBuilder<T, U> mod(NumberColumn<T,U> v);

    WhereClauses<T> ge(NumberColumn<T,U> value);

    WhereClauses<T> gt(NumberColumn<T,U> value);

    WhereClauses<T> le(NumberColumn<T,U> value);

    WhereClauses<T> between(NumberColumn<T,U> a, NumberColumn<T,U> b);

    WhereClauses<T> lt(NumberColumn<T,U> value);

    NumberConditionBuilder<T, U> add(Expression<U> v);

    NumberConditionBuilder<T, U> subtract(Expression<U> v);

    NumberConditionBuilder<T, U> multiply(Expression<U> v);

    NumberConditionBuilder<T, U> divide(Expression<U> v);

    NumberConditionBuilder<T, U> mod(Expression<U> v);

    WhereClauses<T> ge(Expression<U> value);

    WhereClauses<T> gt(Expression<U> value);

    WhereClauses<T> le(Expression<U> value);

    WhereClauses<T> between(Expression<U> a, Expression<U> b);

    WhereClauses<T> lt(Expression<U> value);


}
