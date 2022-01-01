package github.sql.dsl.query.suport.common.model;

import github.sql.dsl.query.api.Operator;
import github.sql.dsl.query.api.BooleanExpression;
import github.sql.dsl.query.api.Expression;

import java.util.List;

public class BooleanOperatorExpression
        extends OperatorExpressionModel<Boolean>
        implements BooleanExpression {

    public BooleanOperatorExpression(Expression<?> expressions, Operator<Boolean> operator) {
        super(expressions, operator);
    }

    public BooleanOperatorExpression(List<? extends Expression<?>> expressions, Operator<Boolean> operator) {
        super(expressions, operator);
    }

    @Override
    public BooleanExpression not() {
        return new BooleanOperatorExpression(this, Operator.NOT);
    }
}
