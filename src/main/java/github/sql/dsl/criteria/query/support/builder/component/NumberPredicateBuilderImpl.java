package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.builder.combination.NumberPredicateBuilder;
import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.Operator;
import github.sql.dsl.criteria.query.expression.path.attribute.NumberAttribute;

import java.util.function.Function;

public class NumberPredicateBuilderImpl<T, U extends Number, NEXT>
        extends AbstractExpressionBuilder<T, U, NEXT>
        implements NumberPredicateBuilder<T, U, NEXT> {

    public NumberPredicateBuilderImpl(Expression<U> exchange,
                                      Operator combined,
                                      boolean negate,
                                      Function<SubPredicate, NEXT> mapper) {
        super(exchange, combined, negate, mapper);
    }

    @Override
    public NumberPredicateBuilder<T, U, NEXT> add(U v) {
        Expression<U> then = expression.then(Operator.ADD, v);
        return new NumberPredicateBuilderImpl<>(then, combined, negate, mapper);
    }

    @Override
    public NumberPredicateBuilder<T, U, NEXT> subtract(U v) {
        Expression<U> then = expression.then(Operator.SUBTRACT, v);
        return new NumberPredicateBuilderImpl<>(then, combined, negate, mapper);
    }

    @Override
    public NumberPredicateBuilder<T, U, NEXT> multiply(U v) {
        Expression<U> then = expression.then(Operator.MULTIPLY, v);
        return new NumberPredicateBuilderImpl<>(then, combined, negate, mapper);
    }

    @Override
    public NumberPredicateBuilder<T, U, NEXT> divide(U v) {
        return new NumberPredicateBuilderImpl<>(
                expression.then(Operator.DIVIDE, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateBuilder<T, U, NEXT> mod(U v) {
        return new NumberPredicateBuilderImpl<>(
                expression.then(Operator.MOD, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateBuilder<T, U, NEXT> nullIf(U value) {
        return new NumberPredicateBuilderImpl<>(
                expression.then(Operator.NULLIF, value),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateBuilder<T, U, NEXT> add(NumberAttribute<T, U> v) {
        return new NumberPredicateBuilderImpl<>(
                expression.then(Operator.ADD, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateBuilder<T, U, NEXT> subtract(NumberAttribute<T, U> v) {
        return new NumberPredicateBuilderImpl<>(
                expression.then(Operator.SUBTRACT, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateBuilder<T, U, NEXT> multiply(NumberAttribute<T, U> v) {
        return new NumberPredicateBuilderImpl<>(
                expression.then(Operator.MULTIPLY, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateBuilder<T, U, NEXT> divide(NumberAttribute<T, U> v) {
        return new NumberPredicateBuilderImpl<>(
                expression.then(Operator.DIVIDE, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateBuilder<T, U, NEXT> mod(NumberAttribute<T, U> v) {
        return new NumberPredicateBuilderImpl<>(
                expression.then(Operator.MOD, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateBuilder<T, U, NEXT> add(Expression<U> v) {
        return new NumberPredicateBuilderImpl<>(
                expression.then(Operator.ADD, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateBuilder<T, U, NEXT> subtract(Expression<U> v) {
        return new NumberPredicateBuilderImpl<>(
                expression.then(Operator.SUBTRACT, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateBuilder<T, U, NEXT> multiply(Expression<U> v) {
        return new NumberPredicateBuilderImpl<>(
                expression.then(Operator.MULTIPLY, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateBuilder<T, U, NEXT> divide(Expression<U> v) {
        return new NumberPredicateBuilderImpl<>(
                expression.then(Operator.DIVIDE, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateBuilder<T, U, NEXT> mod(Expression<U> v) {
        return new NumberPredicateBuilderImpl<>(
                expression.then(Operator.MOD, v),
                combined,
                negate,
                mapper
        );
    }


    @Override
    public NEXT ge(NumberAttribute<T, U> value) {
        return super.ge(value);
    }

    @Override
    public NEXT gt(NumberAttribute<T, U> value) {
        return super.gt(value);
    }

    @Override
    public NEXT le(NumberAttribute<T, U> value) {
        return super.le(value);
    }

    @Override
    public NEXT between(NumberAttribute<T, U> a, NumberAttribute<T, U> b) {
        return super.between(a, b);
    }

    @Override
    public NEXT lt(NumberAttribute<T, U> value) {
        return super.lt(value);
    }

}
