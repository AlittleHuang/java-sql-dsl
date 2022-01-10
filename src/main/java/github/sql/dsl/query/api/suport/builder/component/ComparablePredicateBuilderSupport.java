package github.sql.dsl.query.api.suport.builder.component;

import github.sql.dsl.query.api.builder.combination.ComparablePredicateBuilder;
import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.Operator;

import java.util.function.Function;

public class ComparablePredicateBuilderSupport<T, U extends Comparable<?>, NEXT>
        extends AbstractExpressionBuilder<T, U, NEXT>
        implements ComparablePredicateBuilder<T, U, NEXT> {

    public ComparablePredicateBuilderSupport(Expression<U> exchange,
                                             Operator combined,
                                             boolean negate,
                                             Function<SubPredicate, NEXT> mapper) {
        super(exchange, combined, negate, mapper);
    }

    @Override
    public ComparablePredicateBuilder<T, U, NEXT> nullIf(U value) {
        return new ComparablePredicateBuilderSupport<>(
                expression.then(Operator.NULLIF, value),
                combined, negate, mapper
        );
    }
}
