package github.sql.dsl.query.api.query;

import github.sql.dsl.query.api.expression.ComparableExpressionBuilder;
import github.sql.dsl.query.api.expression.ExpressionBuilder;
import github.sql.dsl.query.api.expression.NumberExpressionBuilder;
import github.sql.dsl.query.api.expression.StringExpressionBuilder;
import github.sql.dsl.query.api.expression.path.Entity;
import github.sql.dsl.query.api.expression.path.PathBuilder;
import github.sql.dsl.query.api.expression.path.bridge.*;

import java.util.Date;

public interface SubPredicateHeaderCombinable<T, NEXT> {

    <U extends Entity> PathBuilder<T, U, NEXT> get(EntityAttributeBridge<T, U> column);

    <U> ExpressionBuilder<T, U, NEXT> get(AttributeBridge<T, U> attribute);

    <U extends Number> NumberExpressionBuilder<T, U, NEXT> get(NumberAttributeBridge<T, U> column);

    <U extends Date> ComparableExpressionBuilder<T, U, NEXT> get(ComparableAttributeBridge<T, U> column);

    StringExpressionBuilder<T, NEXT> get(StringAttributeBridge<T> column);

    <U extends Entity> PathBuilder<T, U, NEXT> not(EntityAttributeBridge<T, U> column);

    <U> ExpressionBuilder<T, U, NEXT> not(AttributeBridge<T, U> attribute);

    <U extends Number> NumberExpressionBuilder<T, U, NEXT> not(NumberAttributeBridge<T, U> column);

    <U extends Date> ComparableExpressionBuilder<T, U, NEXT> not(ComparableAttributeBridge<T, U> column);

    StringExpressionBuilder<T, NEXT> not(StringAttributeBridge<T> column);

}
