package github.sql.dsl.query.api;

import github.sql.dsl.query.suport.common.model.OperatorExpressionModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface Expression<T> {

    default <U> OperatorExpressionModel<U> then(Operator<U> operator, Expression<?>... args) {
        return thenOperator(this, operator, args);
    }

    default <U> OperatorExpression<U> then(Operator<U> operator, List<? extends Expression<?>> args) {
        return thenOperator(this, operator, args);
    }

    static <T, U> OperatorExpressionModel<U> thenOperator(Expression<T> e, Operator<U> operator, Expression<?>... args) {
        if (args == null || args.length == 0) {
            return new OperatorExpressionModel<>(Collections.singletonList(e), operator);
        }
        Expression<?>[] expressions = new Expression[args.length + 1];
        expressions[0] = e;
        System.arraycopy(args, 0, expressions, 1, args.length);
        return new OperatorExpressionModel<>(Arrays.asList(expressions), operator);
    }

    static <T, U> OperatorExpression<U> thenOperator(Expression<T> e, Operator<U> operator, List<? extends Expression<?>> args) {
        return thenOperator(e, operator, args == null ? new Expression[0] : args.toArray(new Expression[0]));
    }

    PathExpression<T> asPathExpression();

    Type getType();

    T getValue();

    Operator<T> getOperator();

    List<? extends Expression<?>> getExpressions();

    enum Type {
        PATH,
        CONSTANT,
        OPERATOR,
    }

}
