package github.sql.dsl.query.suport.builder.component;

import github.sql.dsl.query.api.expression.ConstantExpression;
import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.ExpressionBuilder;
import github.sql.dsl.query.api.expression.Operator;

import java.util.function.Function;

public class ExpressionBuilderImpl<T, U, NEXT>
        extends AbstractExpressionBuilder<T, U, NEXT>
        implements ExpressionBuilder<T, U, NEXT> {

    public ExpressionBuilderImpl(Expression<U> exchange,
                                 Operator combined,
                                 boolean negate,
                                 Function<SubPredicate, NEXT> mapper) {
        super(exchange, combined, negate, mapper);
    }

    @Override
    public ExpressionBuilder<T, U, NEXT> nullIf(U value) {
        Expression<U> expression = this.expression.then(Operator.NULLIF, new ConstantExpression<>(value));
        return new ExpressionBuilderImpl<>(expression, combined, negate, mapper);
    }
}
