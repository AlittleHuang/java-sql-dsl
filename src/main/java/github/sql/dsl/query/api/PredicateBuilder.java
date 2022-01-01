package github.sql.dsl.query.api;

import java.util.Date;

public interface PredicateBuilder<T, V> {

    <U extends Entity> PathBuilder<T, U, V> and(EntityAttributeBridge<T, U> column);

    <U extends Entity> PathBuilder<T, U, V> or(EntityAttributeBridge<T, U> column);

    <U extends Entity> PathBuilder<T, U, V> andNot(EntityAttributeBridge<T, U> column);

    <U extends Entity> PathBuilder<T, U, V> orNot(EntityAttributeBridge<T, U> column);

    <U> ExpressionBuilder<T, U, V> and(AttributeBridge<T, U> attributeBridge);

    <U> ExpressionBuilder<T, U, V> or(AttributeBridge<T, U> attributeBridge);

    <U> ExpressionBuilder<T, U, V> andNot(AttributeBridge<T, U> attributeBridge);

    <U> ExpressionBuilder<T, U, V> orNot(AttributeBridge<T, U> attributeBridge);

    <U extends Number> NumberExpressionBuilder<T, U, V> and(NumberAttributeBridge<T, U> column);

    <U extends Number> NumberExpressionBuilder<T, U, V> or(NumberAttributeBridge<T, U> column);

    <U extends Number> NumberExpressionBuilder<T, U, V> andNot(NumberAttributeBridge<T, U> column);

    <U extends Number> NumberExpressionBuilder<T, U, V> orNot(NumberAttributeBridge<T, U> column);

    <U extends Date> ComparableExpressionBuilder<T, U, V> and(ComparableAttributeBridge<T, U> column);

    <U extends Date> ComparableExpressionBuilder<T, U, V> or(ComparableAttributeBridge<T, U> column);

    <U extends Date> ComparableExpressionBuilder<T, U, V> andNot(ComparableAttributeBridge<T, U> column);

    <U extends Date> ComparableExpressionBuilder<T, U, V> orNot(ComparableAttributeBridge<T, U> column);

    StringExpressionBuilder<T, V> and(StringAttributeBridge<T> column);

    StringExpressionBuilder<T, V> or(StringAttributeBridge<T> column);

    StringExpressionBuilder<T, V> andNot(StringAttributeBridge<T> column);

    StringExpressionBuilder<T, V> orNot(StringAttributeBridge<T> column);

}
