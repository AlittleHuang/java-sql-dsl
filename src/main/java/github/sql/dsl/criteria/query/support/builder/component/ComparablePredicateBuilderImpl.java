package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.builder.combination.ComparablePredicateBuilder;
import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.Operator;

import java.util.function.Function;

public class ComparablePredicateBuilderImpl<T, U extends Comparable<?>, NEXT>
        extends AbstractExpressionBuilder<T, U, NEXT>
        implements ComparablePredicateBuilder<T, U, NEXT> {

    public ComparablePredicateBuilderImpl(Expression<U> exchange,
                                          Operator combined,
                                          boolean negate,
                                          Function<SubPredicate, NEXT> mapper) {
        super(exchange, combined, negate, mapper);
    }

    @Override
    public ComparablePredicateBuilder<T, U, NEXT> nullIf(U value) {
        return new ComparablePredicateBuilderImpl<>(
                expression.then(Operator.NULLIF, value),
                combined, negate, mapper
        );
    }
}
