package github.sql.dsl.criteria.query.expression;

import java.util.Collections;
import java.util.List;

public class OperatorExpressionImpl<T> implements OperatorExpression<T> {

    private final List<? extends Expression<?>> expressions;
    private final Operator operator;

    public OperatorExpressionImpl(Expression<?> expressions, Operator operator) {
        this(Collections.singletonList(expressions), operator);
    }

    public OperatorExpressionImpl(List<? extends Expression<?>> expressions, Operator operator) {
        this.expressions = expressions;
        this.operator = operator;
    }

    @Override
    public List<? extends Expression<?>> getExpressions() {
        return expressions;
    }

    @Override
    public Operator getOperator() {
        return operator;
    }

}