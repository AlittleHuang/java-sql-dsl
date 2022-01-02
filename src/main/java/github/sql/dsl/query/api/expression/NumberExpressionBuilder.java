package github.sql.dsl.query.api.expression;

import github.sql.dsl.query.api.expression.path.bridge.NumberAttributeBridge;

public interface NumberExpressionBuilder<T, U extends Number, V> extends ExpressionBuilder<T, U, V> {

    NumberExpressionBuilder<T, U, V> add(U v);

    NumberExpressionBuilder<T, U, V> subtract(U v);

    NumberExpressionBuilder<T, U, V> multiply(U v);

    NumberExpressionBuilder<T, U, V> divide(U v);

    NumberExpressionBuilder<T, U, V> mod(U v);

    @Override
    NumberExpressionBuilder<T, U, V> nullIf(U value);

    V ge(U value);

    V gt(U value);

    V le(U value);

    V between(U a, U b);

    V lt(U value);


    NumberExpressionBuilder<T, U, V> add(NumberAttributeBridge<T, U> v);

    NumberExpressionBuilder<T, U, V> subtract(NumberAttributeBridge<T, U> v);

    NumberExpressionBuilder<T, U, V> multiply(NumberAttributeBridge<T, U> v);

    NumberExpressionBuilder<T, U, V> divide(NumberAttributeBridge<T, U> v);

    NumberExpressionBuilder<T, U, V> mod(NumberAttributeBridge<T, U> v);

    V ge(NumberAttributeBridge<T, U> value);

    V gt(NumberAttributeBridge<T, U> value);

    V le(NumberAttributeBridge<T, U> value);

    V between(NumberAttributeBridge<T, U> a, NumberAttributeBridge<T, U> b);

    V lt(NumberAttributeBridge<T, U> value);

    NumberExpressionBuilder<T, U, V> add(Expression<U> v);

    NumberExpressionBuilder<T, U, V> subtract(Expression<U> v);

    NumberExpressionBuilder<T, U, V> multiply(Expression<U> v);

    NumberExpressionBuilder<T, U, V> divide(Expression<U> v);

    NumberExpressionBuilder<T, U, V> mod(Expression<U> v);

    V ge(Expression<U> value);

    V gt(Expression<U> value);

    V le(Expression<U> value);

    V between(Expression<U> a, Expression<U> b);

    V lt(Expression<U> value);


}
