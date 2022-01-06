package github.sql.dsl.query.suport.builder.component;

import github.sql.dsl.query.api.expression.BooleanExpression;
import github.sql.dsl.query.api.expression.ConstantExpression;
import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.Operator;

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
