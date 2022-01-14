package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.builder.combination.ComparablePredicateTester;
import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.Operator;

import java.util.function.Function;

public class ComparablePredicateTesterImpl<T, U extends Comparable<?>, NEXT>
        extends AbstractExpressionBuilder<T, U, NEXT>
        implements ComparablePredicateTester<T, U, NEXT> {

    public ComparablePredicateTesterImpl(Expression<U> exchange,
                                         Operator combined,
                                         boolean negate,
                                         Function<SubPredicate, NEXT> mapper) {
        super(exchange, combined, negate, mapper);
    }

    @Override
    public ComparablePredicateTester<T, U, NEXT> nullIf(U value) {
        return new ComparablePredicateTesterImpl<>(
                expression.then(Operator.NULLIF, value),
                combined, negate, mapper
        );
    }
}
