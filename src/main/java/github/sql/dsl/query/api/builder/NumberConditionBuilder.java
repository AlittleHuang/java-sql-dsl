package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.column.NumberColumn;
import github.sql.dsl.query.api.expression.Expression;

public interface NumberConditionBuilder<T, U extends Number, V> extends ConditionBuilder<T, U, V> {

    NumberConditionBuilder<T, U, V> add(U v);

    NumberConditionBuilder<T, U, V> subtract(U v);

    NumberConditionBuilder<T, U, V> multiply(U v);

    NumberConditionBuilder<T, U, V> divide(U v);

    NumberConditionBuilder<T, U, V> mod(U v);

    @Override
    NumberConditionBuilder<T, U, V> nullIf(U value);

    V ge(U value);

    V gt(U value);

    V le(U value);

    V between(U a, U b);

    V lt(U value);


    NumberConditionBuilder<T, U, V> add(NumberColumn<T, U> v);

    NumberConditionBuilder<T, U, V> subtract(NumberColumn<T, U> v);

    NumberConditionBuilder<T, U, V> multiply(NumberColumn<T, U> v);

    NumberConditionBuilder<T, U, V> divide(NumberColumn<T, U> v);

    NumberConditionBuilder<T, U, V> mod(NumberColumn<T, U> v);

    V ge(NumberColumn<T, U> value);

    V gt(NumberColumn<T, U> value);

    V le(NumberColumn<T, U> value);

    V between(NumberColumn<T, U> a, NumberColumn<T, U> b);

    V lt(NumberColumn<T, U> value);

    NumberConditionBuilder<T, U, V> add(Expression<U> v);

    NumberConditionBuilder<T, U, V> subtract(Expression<U> v);

    NumberConditionBuilder<T, U, V> multiply(Expression<U> v);

    NumberConditionBuilder<T, U, V> divide(Expression<U> v);

    NumberConditionBuilder<T, U, V> mod(Expression<U> v);

    V ge(Expression<U> value);

    V gt(Expression<U> value);

    V le(Expression<U> value);

    V between(Expression<U> a, Expression<U> b);

    V lt(Expression<U> value);


}
