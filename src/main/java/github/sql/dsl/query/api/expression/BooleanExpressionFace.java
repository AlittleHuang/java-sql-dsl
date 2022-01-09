package github.sql.dsl.query.api.expression;

import lombok.experimental.Delegate;

import java.util.Objects;

public class BooleanExpressionFace implements BooleanExpression {

    @Delegate
    private final Expression<Boolean> target;

    public BooleanExpressionFace(Expression<Boolean> target) {
        this.target = Objects.requireNonNull(target);
    }

}
