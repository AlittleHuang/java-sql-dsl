package github.sql.dsl.query.api.expression;

import github.sql.dsl.query.api.Operator;

import java.util.List;

public interface Expression<T> {

    <U> OperatorExpression<U> then(Operator<U> operator, Expression<?>... args);

    <U> OperatorExpression<U> then(Operator<U> operator, List<? extends Expression<?>> args);

}
