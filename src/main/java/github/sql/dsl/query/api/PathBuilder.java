package github.sql.dsl.query.api;

import java.util.Date;

public interface PathBuilder<T, U, V> {

    <R extends Entity> PathBuilder<T, R, V> to(EntityAttributeBridge<U, R> column);

    <R extends Number> NumberExpressionBuilder<T, R, V> to(NumberAttributeBridge<U, R> column);

    <R extends Date> ComparableExpressionBuilder<T, R, V> to(ComparableAttributeBridge<U, R> column);

    <R extends Date> ExpressionBuilder<T, R, V> to(AttributeBridge<U, R> attributeBridge);

    StringExpressionBuilder<T, V> to(StringAttributeBridge<U> column);


}
