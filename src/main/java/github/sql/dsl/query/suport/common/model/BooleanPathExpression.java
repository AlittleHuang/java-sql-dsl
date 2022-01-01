package github.sql.dsl.query.suport.common.model;

import github.sql.dsl.query.api.BooleanExpression;
import github.sql.dsl.query.api.Expression;
import github.sql.dsl.query.api.Operator;
import github.sql.dsl.query.api.PathExpression;

public class BooleanPathExpression extends PathExpression<Boolean> implements BooleanExpression {

    public BooleanPathExpression(PathExpression<?> path) {
        super(path);
    }

    @Override
    public BooleanExpression not() {
        return new BooleanOperatorExpression(this, Operator.NOT);
    }


    public static BooleanPathExpression fromPathExpression(Expression<?> pathExpression) {
        if (pathExpression instanceof BooleanPathExpression) {
            return (BooleanPathExpression) pathExpression;
        }
        return new BooleanPathExpression(pathExpression.asPathExpression());
    }
}
