package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.builder.combination.NumberPredicateBuilder;
import github.sql.dsl.criteria.query.builder.combination.StringPredicateBuilder;
import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.Operator;

import java.util.function.Function;

public class StringPredicateBuilderImpl<T, NEXT>
        extends AbstractExpressionBuilder<T, String, NEXT>
        implements StringPredicateBuilder<T, NEXT> {

    public StringPredicateBuilderImpl(Expression<String> exchange,
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
    public StringPredicateBuilder<T, NEXT> nullIf(String value) {
        return new StringPredicateBuilderImpl<>(
                expression.then(Operator.NULLIF, value),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public StringPredicateBuilder<T, NEXT> lower() {
        return new StringPredicateBuilderImpl<>(
                expression.then(Operator.LOWER),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public StringPredicateBuilder<T, NEXT> upper() {
        return new StringPredicateBuilderImpl<>(
                expression.then(Operator.UPPER),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public StringPredicateBuilder<T, NEXT> substring(int a, int b) {
        return new StringPredicateBuilderImpl<>(
                expression.then(Operator.SUBSTRING, a, b),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public StringPredicateBuilder<T, NEXT> substring(int a) {
        return new StringPredicateBuilderImpl<>(
                expression.then(Operator.SUBSTRING, a),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public StringPredicateBuilder<T, NEXT> trim() {
        return new StringPredicateBuilderImpl<>(
                expression.then(Operator.TRIM),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberPredicateBuilder<T, Integer, NEXT> length() {
        return new NumberPredicateBuilderImpl<>(
                expression.then(Operator.LENGTH),
                combined,
                negate,
                mapper
        );
    }
}