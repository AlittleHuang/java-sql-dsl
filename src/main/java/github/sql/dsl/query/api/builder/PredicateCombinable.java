package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.expression.*;
import github.sql.dsl.query.api.expression.path.Entity;
import github.sql.dsl.query.api.expression.path.PathBuilder;
import github.sql.dsl.query.api.expression.path.bridge.*;
import github.sql.dsl.query.api.query.SubPredicateCombinable;
import github.sql.dsl.query.api.query.SubPredicateHeaderCombinable;

import java.util.Date;
import java.util.function.Function;

public interface PredicateCombinable<T, NEXT> {

    <R extends Entity> PathBuilder<T, R, NEXT> and(EntityAttributeBridge<T, R> attribute);

    <R extends Entity> PathBuilder<T, R, NEXT> or(EntityAttributeBridge<T, R> attribute);

    <R extends Entity> PathBuilder<T, R, NEXT> andNot(EntityAttributeBridge<T, R> attribute);

    <R extends Entity> PathBuilder<T, R, NEXT> orNot(EntityAttributeBridge<T, R> attribute);

    <R> ExpressionBuilder<T, R, NEXT> and(AttributeBridge<T, R> attribute);

    <R> ExpressionBuilder<T, R, NEXT> or(AttributeBridge<T, R> attribute);

    <R> ExpressionBuilder<T, R, NEXT> andNot(AttributeBridge<T, R> attribute);

    <R> ExpressionBuilder<T, R, NEXT> orNot(AttributeBridge<T, R> attribute);

    <R extends Number> NumberExpressionBuilder<T, R, NEXT> and(NumberAttributeBridge<T, R> attribute);

    <R extends Number> NumberExpressionBuilder<T, R, NEXT> or(NumberAttributeBridge<T, R> attribute);

    <R extends Number> NumberExpressionBuilder<T, R, NEXT> andNot(NumberAttributeBridge<T, R> attribute);

    <R extends Number> NumberExpressionBuilder<T, R, NEXT> orNot(NumberAttributeBridge<T, R> attribute);

    <R extends Date> ComparableExpressionBuilder<T, R, NEXT> and(ComparableAttributeBridge<T, R> attribute);

    <R extends Date> ComparableExpressionBuilder<T, R, NEXT> or(ComparableAttributeBridge<T, R> attribute);

    <R extends Date> ComparableExpressionBuilder<T, R, NEXT> andNot(ComparableAttributeBridge<T, R> attribute);

    <R extends Date> ComparableExpressionBuilder<T, R, NEXT> orNot(ComparableAttributeBridge<T, R> attribute);

    StringExpressionBuilder<T, NEXT> and(StringAttributeBridge<T> attribute);

    StringExpressionBuilder<T, NEXT> or(StringAttributeBridge<T> attribute);

    StringExpressionBuilder<T, NEXT> andNot(StringAttributeBridge<T> attribute);

    StringExpressionBuilder<T, NEXT> orNot(StringAttributeBridge<T> attribute);

    NEXT And(Function<SubPredicateHeaderCombinable<T, SubPredicateCombinable<T, NEXT>>, BooleanExpression> builder);

    NEXT Or(Function<SubPredicateHeaderCombinable<T, SubPredicateCombinable<T, NEXT>>, BooleanExpression> builder);

    //    NEXT AND(Function<SubPredicateCombinable<T, NEXT>, BooleanExpression> builder);
    //
    //     NEXT OR(Function<SubPredicateCombinable<T, NEXT>, BooleanExpression> builder);

}
