package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.builder.combination.*;
import github.sql.dsl.query.api.expression.BooleanExpression;
import github.sql.dsl.query.api.expression.path.Entity;
import github.sql.dsl.query.api.expression.path.PathBuilder;
import github.sql.dsl.query.api.expression.path.attribute.*;

import java.util.Date;
import java.util.function.Function;

public interface PredicateCombinable<T, NEXT> {

    <R extends Entity> PathBuilder<T, R, NEXT> and(EntityAttribute<T, R> attribute);

    <R extends Entity> PathBuilder<T, R, NEXT> or(EntityAttribute<T, R> attribute);

    <R extends Entity> PathBuilder<T, R, NEXT> andNot(EntityAttribute<T, R> attribute);

    <R extends Entity> PathBuilder<T, R, NEXT> orNot(EntityAttribute<T, R> attribute);

    <R> ExpressionBuilder<T, R, NEXT> and(Attribute<T, R> attribute);

    <R> ExpressionBuilder<T, R, NEXT> or(Attribute<T, R> attribute);

    <R> ExpressionBuilder<T, R, NEXT> andNot(Attribute<T, R> attribute);

    <R> ExpressionBuilder<T, R, NEXT> orNot(Attribute<T, R> attribute);

    <R extends Number> NumberExpressionBuilder<T, R, NEXT> and(NumberAttribute<T, R> attribute);

    <R extends Number> NumberExpressionBuilder<T, R, NEXT> or(NumberAttribute<T, R> attribute);

    <R extends Number> NumberExpressionBuilder<T, R, NEXT> andNot(NumberAttribute<T, R> attribute);

    <R extends Number> NumberExpressionBuilder<T, R, NEXT> orNot(NumberAttribute<T, R> attribute);

    <R extends Date> ComparableExpressionBuilder<T, R, NEXT> and(ComparableAttribute<T, R> attribute);

    <R extends Date> ComparableExpressionBuilder<T, R, NEXT> or(ComparableAttribute<T, R> attribute);

    <R extends Date> ComparableExpressionBuilder<T, R, NEXT> andNot(ComparableAttribute<T, R> attribute);

    <R extends Date> ComparableExpressionBuilder<T, R, NEXT> orNot(ComparableAttribute<T, R> attribute);

    StringExpressionBuilder<T, NEXT> and(StringAttribute<T> attribute);

    StringExpressionBuilder<T, NEXT> or(StringAttribute<T> attribute);

    StringExpressionBuilder<T, NEXT> andNot(StringAttribute<T> attribute);

    StringExpressionBuilder<T, NEXT> orNot(StringAttribute<T> attribute);

    NEXT And(Function<SubPredicateHeaderCombinable<T, SubPredicateCombinable<T, NEXT>>, BooleanExpression> builder);

    NEXT Or(Function<SubPredicateHeaderCombinable<T, SubPredicateCombinable<T, NEXT>>, BooleanExpression> builder);

    //    NEXT AND(Function<SubPredicateCombinable<T, NEXT>, BooleanExpression> builder);
    //
    //     NEXT OR(Function<SubPredicateCombinable<T, NEXT>, BooleanExpression> builder);

}
