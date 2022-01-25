package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.builder.combination.NumberPredicateTester;
import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.Operator;
import github.sql.dsl.criteria.query.expression.path.attribute.NumberAttribute;

import java.util.function.Function;

public class NumberPredicateTesterImpl<T, U extends Number & Comparable<?>, NEXT>
        extends ComparablePredicateTesterImpl<T, U, NEXT>
        implements NumberPredicateTester<T, U, NEXT> {

    public NumberPredicateTesterImpl(Expression<U> exchange,
                                     Operator combined,
                                     boolean negate,
                                     Function<SubPredicate, NEXT> mapper) {
        super(exchange, combined, negate, mapper);
    }

    @Override
    public NumberPredicateTester<T, U, NEXT> add(U v) {
        Expression<U> then = expression.then(Operator.ADD, v);
        return new NumberPredicateTesterImpl<>(then, combined, negate, mapper);
    }

    @Override
    public NumberPredicateTester<T, U, NEXT> subtract(U v) {
        Expression<U> then = expression.then(Operator.SUBTRACT, v);
        return new NumberPredicateTesterImpl<>(then, combined, negate, mapper);
    }

    @Override
    public NumberPredicateTester<T, U, NEXT> multiply(U v) {
        Expression<U> then = expression.then(Operator.MULTIPLY, v);
        return new NumberPredicateTesterImpl<>(then, combined, negate, mapper);
    }

    @Override
    public NumberPredicateTester<T, U, NEXT> divide(U v) {
        return new NumberPredicateTesterImpl<>(
                expression.then(Operator.DIVIDE, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateTester<T, U, NEXT> mod(U v) {
        return new NumberPredicateTesterImpl<>(
                expression.then(Operator.MOD, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateTester<T, U, NEXT> nullIf(U value) {
        return new NumberPredicateTesterImpl<>(
                expression.then(Operator.NULLIF, value),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateTester<T, U, NEXT> add(NumberAttribute<T, U> v) {
        return new NumberPredicateTesterImpl<>(
                expression.then(Operator.ADD, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateTester<T, U, NEXT> subtract(NumberAttribute<T, U> v) {
        return new NumberPredicateTesterImpl<>(
                expression.then(Operator.SUBTRACT, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateTester<T, U, NEXT> multiply(NumberAttribute<T, U> v) {
        return new NumberPredicateTesterImpl<>(
                expression.then(Operator.MULTIPLY, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateTester<T, U, NEXT> divide(NumberAttribute<T, U> v) {
        return new NumberPredicateTesterImpl<>(
                expression.then(Operator.DIVIDE, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateTester<T, U, NEXT> mod(NumberAttribute<T, U> v) {
        return new NumberPredicateTesterImpl<>(
                expression.then(Operator.MOD, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateTester<T, U, NEXT> add(Expression<U> v) {
        return new NumberPredicateTesterImpl<>(
                expression.then(Operator.ADD, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateTester<T, U, NEXT> subtract(Expression<U> v) {
        return new NumberPredicateTesterImpl<>(
                expression.then(Operator.SUBTRACT, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateTester<T, U, NEXT> multiply(Expression<U> v) {
        return new NumberPredicateTesterImpl<>(
                expression.then(Operator.MULTIPLY, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateTester<T, U, NEXT> divide(Expression<U> v) {
        return new NumberPredicateTesterImpl<>(
                expression.then(Operator.DIVIDE, v),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateTester<T, U, NEXT> mod(Expression<U> v) {
        return new NumberPredicateTesterImpl<>(
                expression.then(Operator.MOD, v),
                combined,
                negate,
                mapper
        );
    }

}
