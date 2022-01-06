package github.sql.dsl.query.suport.builder.component;

import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.NumberExpressionBuilder;
import github.sql.dsl.query.api.expression.Operator;
import github.sql.dsl.query.api.expression.path.bridge.NumberAttributeBridge;

import java.util.function.Function;

public class NumberExpressionBuilderImpl<T, U extends Number, NEXT>
        extends AbstractExpressionBuilder<T, U, NEXT>
        implements NumberExpressionBuilder<T, U, NEXT> {

    public NumberExpressionBuilderImpl(Expression<U> exchange,
                                       Operator combined,
                                       boolean negate,
                                       Function<SubPredicate, NEXT> mapper) {
        super(exchange, combined, negate, mapper);
    }

    @Override
    public NumberExpressionBuilder<T, U, NEXT> add(U v) {
        Expression<U> then = expression.then(Operator.ADD, v);
        return new NumberExpressionBuilderImpl<>(then, combined, negate, mapper);
    }

    @Override
    public NumberExpressionBuilder<T, U, NEXT> subtract(U v) {
        Expression<U> then = expression.then(Operator.SUBTRACT, v);
        return new NumberExpressionBuilderImpl<>(then, combined, negate, mapper);
    }

    @Override
    public NumberExpressionBuilder<T, U, NEXT> multiply(U v) {
        Expression<U> then = expression.then(Operator.MULTIPLY, v);
        return new NumberExpressionBuilderImpl<>(then, combined, negate, mapper);
    }

    @Override
    public NumberExpressionBuilder<T, U, NEXT> divide(U v) {
        return new NumberExpressionBuilderImpl<>(
                expression.then(Operator.DIVIDE, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberExpressionBuilder<T, U, NEXT> mod(U v) {
        return new NumberExpressionBuilderImpl<>(
                expression.then(Operator.MOD, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberExpressionBuilder<T, U, NEXT> nullIf(U value) {
        return new NumberExpressionBuilderImpl<>(
                expression.then(Operator.NULLIF, value),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberExpressionBuilder<T, U, NEXT> add(NumberAttributeBridge<T, U> v) {
        return new NumberExpressionBuilderImpl<>(
                expression.then(Operator.ADD, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberExpressionBuilder<T, U, NEXT> subtract(NumberAttributeBridge<T, U> v) {
        return new NumberExpressionBuilderImpl<>(
                expression.then(Operator.SUBTRACT, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberExpressionBuilder<T, U, NEXT> multiply(NumberAttributeBridge<T, U> v) {
        return new NumberExpressionBuilderImpl<>(
                expression.then(Operator.MULTIPLY, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberExpressionBuilder<T, U, NEXT> divide(NumberAttributeBridge<T, U> v) {
        return new NumberExpressionBuilderImpl<>(
                expression.then(Operator.DIVIDE, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberExpressionBuilder<T, U, NEXT> mod(NumberAttributeBridge<T, U> v) {
        return new NumberExpressionBuilderImpl<>(
                expression.then(Operator.MOD, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberExpressionBuilder<T, U, NEXT> add(Expression<U> v) {
        return new NumberExpressionBuilderImpl<>(
                expression.then(Operator.ADD, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberExpressionBuilder<T, U, NEXT> subtract(Expression<U> v) {
        return new NumberExpressionBuilderImpl<>(
                expression.then(Operator.SUBTRACT, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberExpressionBuilder<T, U, NEXT> multiply(Expression<U> v) {
        return new NumberExpressionBuilderImpl<>(
                expression.then(Operator.MULTIPLY, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberExpressionBuilder<T, U, NEXT> divide(Expression<U> v) {
        return new NumberExpressionBuilderImpl<>(
                expression.then(Operator.DIVIDE, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberExpressionBuilder<T, U, NEXT> mod(Expression<U> v) {
        return new NumberExpressionBuilderImpl<>(
                expression.then(Operator.MOD, v),
                combined,
                negate,
                mapper
        );
    }


    @Override
    public NEXT ge(NumberAttributeBridge<T, U> value) {
        return super.ge(value);
    }

    @Override
    public NEXT gt(NumberAttributeBridge<T, U> value) {
        return super.gt(value);
    }

    @Override
    public NEXT le(NumberAttributeBridge<T, U> value) {
        return super.le(value);
    }

    @Override
    public NEXT between(NumberAttributeBridge<T, U> a, NumberAttributeBridge<T, U> b) {
        return super.between(a, b);
    }

    @Override
    public NEXT lt(NumberAttributeBridge<T, U> value) {
        return super.lt(value);
    }

}
