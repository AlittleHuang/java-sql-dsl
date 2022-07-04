package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.expression.SqlExpression;
import github.sql.dsl.criteria.query.expression.Operator;
import lombok.Getter;


@Getter
public class SubExpression<T> {

    protected final SqlExpression<T> expression;
    protected final Operator combined;
    protected final boolean negate;

    public SubExpression(SqlExpression<T> expression, Operator combined, boolean negate) {
        this.expression = expression;
        this.combined = combined;
        this.negate = negate;
    }

}
