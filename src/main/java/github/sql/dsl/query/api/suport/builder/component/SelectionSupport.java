package github.sql.dsl.query.api.suport.builder.component;

import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.Operator;
import github.sql.dsl.query.api.expression.PathExpression;

import java.util.List;
import java.util.Objects;

public class SelectionSupport<T> implements Selection<T> {

    private final Expression<T> expression;
    private final AggregateFunction aggregateFunction;

    public SelectionSupport(Expression<T> expression, AggregateFunction aggregateFunction) {
        this.expression = Objects.requireNonNull(expression, "expression");
        this.aggregateFunction = aggregateFunction;
    }

    public AggregateFunction getAggregateFunction() {
        return aggregateFunction;
    }

    @Override
    public PathExpression<T> asPathExpression() {
        return expression.asPathExpression();
    }

    @Override
    public Type getType() {
        return expression.getType();
    }

    @Override
    public T getValue() {
        return expression.getValue();
    }

    @Override
    public Operator getOperator() {
        return expression.getOperator();
    }

    @Override
    public List<? extends Expression<?>> getExpressions() {
        return expression.getExpressions();
    }
}
