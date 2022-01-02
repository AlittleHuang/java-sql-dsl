package github.sql.dsl.query.suport.builder.component;

import github.sql.dsl.query.api.expression.BooleanExpression;
import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.Operator;

import java.util.List;

public class BooleanOperatorExpression
        extends OperatorExpressionImpl<Boolean>
        implements BooleanExpression {

    public BooleanOperatorExpression(Expression<?> expressions, Operator operator) {
        super(expressions, operator);
    }

    public BooleanOperatorExpression(List<? extends Expression<?>> expressions, Operator operator) {
        super(expressions, operator);
    }

    @Override
    public BooleanExpression not() {
        return new BooleanOperatorExpression(this, Operator.NOT);
    }
}
