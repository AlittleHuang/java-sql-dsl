package github.sql.dsl.query.api;

import java.util.Date;
import java.util.function.Function;

public interface QueryBuilder<T> extends TypeQueryBuilder<T> {

    <U extends Entity> PathBuilder<T, U, Predicate<T>> where(EntityAttributeBridge<T, U> column);

    <U> ExpressionBuilder<T, U, Predicate<T>> where(AttributeBridge<T, U> attributeBridge);

    <U extends Number> NumberExpressionBuilder<T, U, Predicate<T>> where(NumberAttributeBridge<T, U> column);

    <U extends Date> ComparableExpressionBuilder<T, U, Predicate<T>> where(ComparableAttributeBridge<T, U> column);

    StringExpressionBuilder<T, Predicate<T>> where(StringAttributeBridge<T> column);

    <U extends Entity> PathBuilder<T, U, Predicate<T>> whereNot(EntityAttributeBridge<T, U> column);

    <U> ExpressionBuilder<T, U, Predicate<T>> whereNot(AttributeBridge<T, U> attributeBridge);

    <U extends Number> NumberExpressionBuilder<T, U, Predicate<T>> whereNot(NumberAttributeBridge<T, U> column);

    <U extends Date> ComparableExpressionBuilder<T, U, Predicate<T>> whereNot(ComparableAttributeBridge<T, U> column);

    StringExpressionBuilder<T, Predicate<T>> whereNot(StringAttributeBridge<T> column);

    Predicate<T> wheres(Function<Predicate.Builder<T>, BooleanExpression> column);

}
