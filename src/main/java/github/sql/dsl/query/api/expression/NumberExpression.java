package github.sql.dsl.query.api.expression;

public interface NumberExpression<T> extends Expression<T> {

    NumberExpression<T> add(T v);

    NumberExpression<T> subtract(T v);

    NumberExpression<T> multiply(T v);

    NumberExpression<T> divide(T v);

    NumberExpression<T> mod(T v);

}
