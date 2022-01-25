package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.builder.combination.NumberPredicateTester;
import github.sql.dsl.criteria.query.builder.combination.StringPredicateTester;
import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.Operator;

import java.util.function.Function;

public class StringPredicateTesterImpl<T, NEXT>
        extends ComparablePredicateTesterImpl<T, String, NEXT>
        implements StringPredicateTester<T, NEXT> {

    public StringPredicateTesterImpl(Expression<String> exchange,
                                     Operator combined,
                                     boolean negate,
                                     Function<SubPredicate, NEXT> mapper) {
        super(exchange, combined, negate, mapper);
    }

    @Override
    public NEXT like(String value) {
        return next(Operator.LIKE, value);
    }

    @Override
    public NEXT startWith(String value) {
        return like("%" + value);
    }

    @Override
    public NEXT startEndWith(String value) {
        return like(value + "%");
    }

    @Override
    public NEXT contains(String value) {
        return like("%" + value + "%");
    }

    @Override
    public StringPredicateTester<T, NEXT> nullIf(String value) {
        return new StringPredicateTesterImpl<>(
                expression.then(Operator.NULLIF, value),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public StringPredicateTester<T, NEXT> lower() {
        return new StringPredicateTesterImpl<>(
                expression.then(Operator.LOWER),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public StringPredicateTester<T, NEXT> upper() {
        return new StringPredicateTesterImpl<>(
                expression.then(Operator.UPPER),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public StringPredicateTester<T, NEXT> substring(int a, int b) {
        return new StringPredicateTesterImpl<>(
                expression.then(Operator.SUBSTRING, a, b),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public StringPredicateTester<T, NEXT> substring(int a) {
        return new StringPredicateTesterImpl<>(
                expression.then(Operator.SUBSTRING, a),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public StringPredicateTester<T, NEXT> trim() {
        return new StringPredicateTesterImpl<>(
                expression.then(Operator.TRIM),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateTester<T, Integer, NEXT> length() {
        return new NumberPredicateTesterImpl<>(
                expression.then(Operator.LENGTH),
                combined,
                negate,
                mapper
        );
    }
}