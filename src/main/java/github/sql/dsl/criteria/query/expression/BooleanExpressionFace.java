package github.sql.dsl.criteria.query.expression;

import lombok.experimental.Delegate;

import java.util.Objects;

public class BooleanExpressionFace implements BooleanExpression {

    @Delegate
    protected final Expression<Boolean> target;

    public BooleanExpressionFace(Expression<Boolean> target) {
        this.target = Objects.requireNonNull(target);
    }

}