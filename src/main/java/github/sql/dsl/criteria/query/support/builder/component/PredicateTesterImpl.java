package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.builder.combination.PredicateTester;
import github.sql.dsl.criteria.query.expression.ConstantExpression;
import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.Operator;

import java.util.function.Function;

public class PredicateTesterImpl<T, U, NEXT>
        extends AbstractExpressionBuilder<T, U, NEXT>
        implements PredicateTester<T, U, NEXT> {

    public PredicateTesterImpl(Expression<U> expression,
                               Operator combined,
                               boolean negate,
                               Function<SubPredicate, NEXT> mapper) {
        super(expression, combined, negate, mapper);
    }

    @Override
    public PredicateTester<T, U, NEXT> nullIf(U value) {
        Expression<U> expression = this.expression.then(Operator.NULLIF, new ConstantExpression<>(value));
        return new PredicateTesterImpl<>(expression, combined, negate, mapper);
    }
}
