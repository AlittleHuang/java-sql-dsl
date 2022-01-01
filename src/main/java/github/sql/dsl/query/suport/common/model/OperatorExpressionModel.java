package github.sql.dsl.query.suport.common.model;

import github.sql.dsl.query.api.Expression;
import github.sql.dsl.query.api.Operator;
import github.sql.dsl.query.api.OperatorExpression;

import java.util.Collections;
import java.util.List;

public class OperatorExpressionModel<T> implements OperatorExpression<T> {

    private final List<? extends Expression<?>> expressions;
    private final Operator<T> operator;

    public OperatorExpressionModel(Expression<?> expressions, Operator<T> operator) {
        this(Collections.singletonList(expressions), operator);
    }

    public OperatorExpressionModel(List<? extends Expression<?>> expressions, Operator<T> operator) {
        this.expressions = expressions;
        this.operator = operator;
    }

    @Override
    public List<? extends Expression<?>> getExpressions() {
        return expressions;
    }

    @Override
    public Operator<T> getOperator() {
        return operator;
    }

}
