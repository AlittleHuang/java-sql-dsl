package github.sql.dsl.query.suport.common.model;

import github.sql.dsl.query.api.Expression;
import github.sql.dsl.query.api.Operator;
import github.sql.dsl.query.api.BooleanExpression;
import github.sql.dsl.query.api.ConstantExpression;

public class BooleanConstantExpression extends ConstantExpression<Boolean> implements BooleanExpression {


    public BooleanConstantExpression(Boolean value) {
        super(value);
    }

    public static BooleanConstantExpression fromConstantExpression(Expression<Boolean> expression) {
        if (expression instanceof BooleanConstantExpression) {
            return (BooleanConstantExpression) expression;
        }
        return new BooleanConstantExpression(expression.getValue());
    }

    @Override
    public BooleanExpression not() {
        return new BooleanOperatorExpression(this, Operator.NOT);
    }

}
