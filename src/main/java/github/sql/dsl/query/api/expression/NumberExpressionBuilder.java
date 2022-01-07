package github.sql.dsl.query.api.expression;

import github.sql.dsl.query.api.expression.path.bridge.NumberAttribute;

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


    NumberExpressionBuilder<T, U, V> add(NumberAttribute<T, U> v);

    NumberExpressionBuilder<T, U, V> subtract(NumberAttribute<T, U> v);

    NumberExpressionBuilder<T, U, V> multiply(NumberAttribute<T, U> v);

    NumberExpressionBuilder<T, U, V> divide(NumberAttribute<T, U> v);

    NumberExpressionBuilder<T, U, V> mod(NumberAttribute<T, U> v);

    V ge(NumberAttribute<T, U> value);

    V gt(NumberAttribute<T, U> value);

    V le(NumberAttribute<T, U> value);

    V between(NumberAttribute<T, U> a, NumberAttribute<T, U> b);

    V lt(NumberAttribute<T, U> value);

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
