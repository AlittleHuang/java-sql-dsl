package github.sql.dsl.query.suport.builder.component;

import github.sql.dsl.query.api.builder.combination.ComparableExpressionBuilder;
import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.Operator;

import java.util.function.Function;

public class ComparableExpressionBuilderImpl<T, U extends Comparable<?>, NEXT>
        extends AbstractExpressionBuilder<T, U, NEXT>
        implements ComparableExpressionBuilder<T, U, NEXT> {

    public ComparableExpressionBuilderImpl(Expression<U> exchange,
                                           Operator combined,
                                           boolean negate,
                                           Function<SubPredicate, NEXT> mapper) {
        super(exchange, combined, negate, mapper);
    }

    @Override
    public ComparableExpressionBuilder<T, U, NEXT> nullIf(U value) {
        return new ComparableExpressionBuilderImpl<>(
                expression.then(Operator.NULLIF, value),
                combined, negate, mapper
        );
    }
}
