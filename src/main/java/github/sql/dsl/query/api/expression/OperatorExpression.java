package github.sql.dsl.query.api.expression;

import github.sql.dsl.query.api.Operator;

import java.util.List;

public interface OperatorExpression<T> extends Expression<T> {

    Operator<T> getOperator();

    List<? extends Expression<?>> getExpressions();

}
