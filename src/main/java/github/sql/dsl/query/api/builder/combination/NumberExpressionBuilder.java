package github.sql.dsl.query.api.builder.combination;

import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.path.attribute.NumberAttribute;

public interface NumberExpressionBuilder<T, U extends Number, NEXT> extends ExpressionBuilder<T, U, NEXT> {

    NumberExpressionBuilder<T, U, NEXT> add(U v);

    NumberExpressionBuilder<T, U, NEXT> subtract(U v);

    NumberExpressionBuilder<T, U, NEXT> multiply(U v);

    NumberExpressionBuilder<T, U, NEXT> divide(U v);

    NumberExpressionBuilder<T, U, NEXT> mod(U v);

    @Override
    NumberExpressionBuilder<T, U, NEXT> nullIf(U value);

    NEXT ge(U value);

    NEXT gt(U value);

    NEXT le(U value);

    NEXT between(U a, U b);

    NEXT lt(U value);


    NumberExpressionBuilder<T, U, NEXT> add(NumberAttribute<T, U> v);

    NumberExpressionBuilder<T, U, NEXT> subtract(NumberAttribute<T, U> v);

    NumberExpressionBuilder<T, U, NEXT> multiply(NumberAttribute<T, U> v);

    NumberExpressionBuilder<T, U, NEXT> divide(NumberAttribute<T, U> v);

    NumberExpressionBuilder<T, U, NEXT> mod(NumberAttribute<T, U> v);

    NEXT ge(NumberAttribute<T, U> value);

    NEXT gt(NumberAttribute<T, U> value);

    NEXT le(NumberAttribute<T, U> value);

    NEXT between(NumberAttribute<T, U> a, NumberAttribute<T, U> b);

    NEXT lt(NumberAttribute<T, U> value);

    NumberExpressionBuilder<T, U, NEXT> add(Expression<U> v);

    NumberExpressionBuilder<T, U, NEXT> subtract(Expression<U> v);

    NumberExpressionBuilder<T, U, NEXT> multiply(Expression<U> v);

    NumberExpressionBuilder<T, U, NEXT> divide(Expression<U> v);

    NumberExpressionBuilder<T, U, NEXT> mod(Expression<U> v);

    NEXT ge(Expression<U> value);

    NEXT gt(Expression<U> value);

    NEXT le(Expression<U> value);

    NEXT between(Expression<U> a, Expression<U> b);

    NEXT lt(Expression<U> value);


}
