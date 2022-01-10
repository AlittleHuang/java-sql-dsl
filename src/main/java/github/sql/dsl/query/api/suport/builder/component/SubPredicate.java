package github.sql.dsl.query.api.suport.builder.component;

import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.Operator;

public class SubPredicate extends SubExpression<Boolean> {

    public SubPredicate(Expression<Boolean> expression, Operator combined, boolean negate) {
        super(expression, combined, negate);
    }


}
