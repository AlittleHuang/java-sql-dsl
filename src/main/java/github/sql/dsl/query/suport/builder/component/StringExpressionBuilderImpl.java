package github.sql.dsl.query.suport.builder.component;

import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.NumberExpressionBuilder;
import github.sql.dsl.query.api.expression.Operator;
import github.sql.dsl.query.api.expression.StringExpressionBuilder;

import java.util.function.Function;

public class StringExpressionBuilderImpl<T, NEXT>
        extends AbstractExpressionBuilder<T, String, NEXT>
        implements StringExpressionBuilder<T, NEXT> {

    public StringExpressionBuilderImpl(Expression<String> exchange,
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
    public StringExpressionBuilder<T, NEXT> nullIf(String value) {
        return new StringExpressionBuilderImpl<>(
                expression.then(Operator.NULLIF, value),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public StringExpressionBuilder<T, NEXT> lower() {
        return new StringExpressionBuilderImpl<>(
                expression.then(Operator.LOWER),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public StringExpressionBuilder<T, NEXT> upper() {
        return new StringExpressionBuilderImpl<>(
                expression.then(Operator.UPPER),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public StringExpressionBuilder<T, NEXT> substring(int a, int b) {
        return new StringExpressionBuilderImpl<>(
                expression.then(Operator.SUBSTRING, a, b),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public StringExpressionBuilder<T, NEXT> substring(int a) {
        return new StringExpressionBuilderImpl<>(
                expression.then(Operator.SUBSTRING, a),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public StringExpressionBuilder<T, NEXT> trim() {
        return new StringExpressionBuilderImpl<>(
                expression.then(Operator.TRIM),
                combined,
                negate,
                mapper
        );
    }

    @Override
    public NumberExpressionBuilder<T, Integer, NEXT> length() {
        return new NumberExpressionBuilderImpl<>(
                expression.then(Operator.LENGTH),
                combined,
                negate,
                mapper
        );
    }
}