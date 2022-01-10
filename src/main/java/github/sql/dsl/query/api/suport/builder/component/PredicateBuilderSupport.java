package github.sql.dsl.query.api.suport.builder.component;

import github.sql.dsl.query.api.builder.combination.PredicateBuilder;
import github.sql.dsl.query.api.expression.ConstantExpression;
import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.Operator;

import java.util.function.Function;

public class PredicateBuilderSupport<T, U, NEXT>
        extends AbstractExpressionBuilder<T, U, NEXT>
        implements PredicateBuilder<T, U, NEXT> {

    public PredicateBuilderSupport(Expression<U> expression,
                                   Operator combined,
                                   boolean negate,
                                   Function<SubPredicate, NEXT> mapper) {
        super(expression, combined, negate, mapper);
    }

    @Override
    public PredicateBuilder<T, U, NEXT> nullIf(U value) {
        Expression<U> expression = this.expression.then(Operator.NULLIF, new ConstantExpression<>(value));
        return new PredicateBuilderSupport<>(expression, combined, negate, mapper);
    }
}
