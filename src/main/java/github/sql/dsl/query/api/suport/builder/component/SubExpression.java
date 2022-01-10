package github.sql.dsl.query.api.suport.builder.component;

import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.Operator;
import lombok.Getter;


@Getter
public class SubExpression<T> {

    protected final Expression<T> expression;
    protected final Operator combined;
    protected final boolean negate;

    public SubExpression(Expression<T> expression, Operator combined, boolean negate) {
        this.expression = expression;
        this.combined = combined;
        this.negate = negate;
    }

}
