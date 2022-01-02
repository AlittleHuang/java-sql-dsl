package github.sql.dsl.query.api.query;

import github.sql.dsl.query.api.expression.*;
import github.sql.dsl.query.api.expression.path.Entity;
import github.sql.dsl.query.api.expression.path.PathBuilder;
import github.sql.dsl.query.api.expression.path.bridge.*;

import java.util.Date;
import java.util.function.Function;

public interface Whereable<T, NEXT> {

    <U extends Entity> PathBuilder<T, U, NEXT> where(EntityAttributeBridge<T, U> column);

    <U> ExpressionBuilder<T, U, NEXT> where(AttributeBridge<T, U> attribute);

    <U extends Number> NumberExpressionBuilder<T, U, NEXT> where(NumberAttributeBridge<T, U> column);

    <U extends Date> ComparableExpressionBuilder<T, U, NEXT> where(ComparableAttributeBridge<T, U> column);

    StringExpressionBuilder<T, NEXT> where(StringAttributeBridge<T> column);

    <U extends Entity> PathBuilder<T, U, NEXT> whereNot(EntityAttributeBridge<T, U> column);

    <U> ExpressionBuilder<T, U, NEXT> whereNot(AttributeBridge<T, U> attribute);

    <U extends Number> NumberExpressionBuilder<T, U, NEXT> whereNot(NumberAttributeBridge<T, U> column);

    <U extends Date> ComparableExpressionBuilder<T, U, NEXT> whereNot(ComparableAttributeBridge<T, U> column);

    StringExpressionBuilder<T, NEXT> whereNot(StringAttributeBridge<T> column);

    NEXT Where(Function<SubPredicateHeaderCombinable<T, SubPredicateCombinable<T, NEXT>>, BooleanExpression> builder);


}
