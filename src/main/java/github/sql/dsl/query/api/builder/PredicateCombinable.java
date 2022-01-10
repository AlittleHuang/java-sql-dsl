package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.builder.combination.*;
import github.sql.dsl.query.api.expression.BooleanExpression;
import github.sql.dsl.query.api.expression.Predicate;
import github.sql.dsl.query.api.expression.path.Entity;
import github.sql.dsl.query.api.expression.path.PathBuilder;
import github.sql.dsl.query.api.expression.path.attribute.*;

import java.util.Date;

public interface PredicateCombinable<T, NEXT> {

    <R extends Entity> PathBuilder<T, R, NEXT> and(EntityAttribute<T, R> attribute);

    <R extends Entity> PathBuilder<T, R, NEXT> or(EntityAttribute<T, R> attribute);

    <R extends Entity> PathBuilder<T, R, NEXT> andNot(EntityAttribute<T, R> attribute);

    <R extends Entity> PathBuilder<T, R, NEXT> orNot(EntityAttribute<T, R> attribute);

    <R> PredicateBuilder<T, R, NEXT> and(Attribute<T, R> attribute);

    <R> PredicateBuilder<T, R, NEXT> or(Attribute<T, R> attribute);

    <R> PredicateBuilder<T, R, NEXT> andNot(Attribute<T, R> attribute);

    <R> PredicateBuilder<T, R, NEXT> orNot(Attribute<T, R> attribute);

    <R extends Number> NumberPredicateBuilder<T, R, NEXT> and(NumberAttribute<T, R> attribute);

    <R extends Number> NumberPredicateBuilder<T, R, NEXT> or(NumberAttribute<T, R> attribute);

    <R extends Number> NumberPredicateBuilder<T, R, NEXT> andNot(NumberAttribute<T, R> attribute);

    <R extends Number> NumberPredicateBuilder<T, R, NEXT> orNot(NumberAttribute<T, R> attribute);

    <R extends Date> ComparablePredicateBuilder<T, R, NEXT> and(ComparableAttribute<T, R> attribute);

    <R extends Date> ComparablePredicateBuilder<T, R, NEXT> or(ComparableAttribute<T, R> attribute);

    <R extends Date> ComparablePredicateBuilder<T, R, NEXT> andNot(ComparableAttribute<T, R> attribute);

    <R extends Date> ComparablePredicateBuilder<T, R, NEXT> orNot(ComparableAttribute<T, R> attribute);

    StringPredicateBuilder<T, NEXT> and(StringAttribute<T> attribute);

    StringPredicateBuilder<T, NEXT> or(StringAttribute<T> attribute);

    StringPredicateBuilder<T, NEXT> andNot(StringAttribute<T> attribute);

    StringPredicateBuilder<T, NEXT> orNot(StringAttribute<T> attribute);

    NEXT andAppend(Builder<T, NEXT> builder);

    NEXT orAppend(Builder<T, NEXT> builder);

    NEXT and(Predicate<T> predicate);

    NEXT or(Predicate<T> predicate);

    interface Builder<T, NEXT> {
        BooleanExpression build(SubPredicateHeaderCombinable<T, SubPredicateCombinable<T, NEXT>> builder);
    }

}
