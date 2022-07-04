package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.expression.SqlExpression;
import github.sql.dsl.criteria.query.expression.Operator;

public class SubPredicate extends SubExpression<Boolean> {

    public SubPredicate(SqlExpression<Boolean> expression, Operator combined, boolean negate) {
        super(expression, combined, negate);
    }


}
