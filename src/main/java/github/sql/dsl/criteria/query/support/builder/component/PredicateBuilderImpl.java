package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.builder.combination.PredicateBuilder;
import github.sql.dsl.criteria.query.expression.ConstantExpression;
import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.Operator;

import java.util.function.Function;

public class PredicateBuilderImpl<T, U, NEXT>
        extends AbstractExpressionBuilder<T, U, NEXT>
        implements PredicateBuilder<T, U, NEXT> {

    public PredicateBuilderImpl(Expression<U> expression,
                                Operator combined,
                                boolean negate,
                                Function<SubPredicate, NEXT> mapper) {
        super(expression, combined, negate, mapper);
    }

    @Override
    public PredicateBuilder<T, U, NEXT> nullIf(U value) {
        Expression<U> expression = this.expression.then(Operator.NULLIF, new ConstantExpression<>(value));
        return new PredicateBuilderImpl<>(expression, combined, negate, mapper);
    }
}
