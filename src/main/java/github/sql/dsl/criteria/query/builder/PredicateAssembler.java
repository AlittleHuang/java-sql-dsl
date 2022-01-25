package github.sql.dsl.criteria.query.builder;

import github.sql.dsl.criteria.query.builder.combination.ComparablePredicateTester;
import github.sql.dsl.criteria.query.builder.combination.NumberPredicateTester;
import github.sql.dsl.criteria.query.builder.combination.PredicateTester;
import github.sql.dsl.criteria.query.builder.combination.StringPredicateTester;
import github.sql.dsl.criteria.query.expression.Predicate;
import github.sql.dsl.criteria.query.expression.path.Entity;
import github.sql.dsl.criteria.query.expression.path.PathBuilder;
import github.sql.dsl.criteria.query.expression.path.attribute.*;

public interface PredicateAssembler<T, NEXT> {

    <R extends Entity> PathBuilder<T, R, NEXT> and(EntityAttribute<T, R> attribute);

    <R extends Entity> PathBuilder<T, R, NEXT> or(EntityAttribute<T, R> attribute);

    <R extends Entity> PathBuilder<T, R, NEXT> andNot(EntityAttribute<T, R> attribute);

    <R extends Entity> PathBuilder<T, R, NEXT> orNot(EntityAttribute<T, R> attribute);

    <R> PredicateTester<T, R, NEXT> and(Attribute<T, R> attribute);

    <R> PredicateTester<T, R, NEXT> or(Attribute<T, R> attribute);

    <R> PredicateTester<T, R, NEXT> andNot(Attribute<T, R> attribute);

    <R> PredicateTester<T, R, NEXT> orNot(Attribute<T, R> attribute);

    <R extends Number & Comparable<?>> NumberPredicateTester<T, R, NEXT> and(NumberAttribute<T, R> attribute);

    <R extends Number & Comparable<?>> NumberPredicateTester<T, R, NEXT> or(NumberAttribute<T, R> attribute);

    <R extends Number & Comparable<?>> NumberPredicateTester<T, R, NEXT> andNot(NumberAttribute<T, R> attribute);

    <R extends Number & Comparable<?>> NumberPredicateTester<T, R, NEXT> orNot(NumberAttribute<T, R> attribute);

    <R extends Comparable<?>> ComparablePredicateTester<T, R, NEXT> and(ComparableAttribute<T, R> attribute);

    <R extends Comparable<?>> ComparablePredicateTester<T, R, NEXT> or(ComparableAttribute<T, R> attribute);

    <R extends Comparable<?>> ComparablePredicateTester<T, R, NEXT> andNot(ComparableAttribute<T, R> attribute);

    <R extends Comparable<?>> ComparablePredicateTester<T, R, NEXT> orNot(ComparableAttribute<T, R> attribute);

    StringPredicateTester<T, NEXT> and(StringAttribute<T> attribute);

    StringPredicateTester<T, NEXT> or(StringAttribute<T> attribute);

    StringPredicateTester<T, NEXT> andNot(StringAttribute<T> attribute);

    StringPredicateTester<T, NEXT> orNot(StringAttribute<T> attribute);

    // NEXT andAppend(Builder<T, NEXT> builder);
    //
    // NEXT orAppend(Builder<T, NEXT> builder);

    NEXT and(Predicate<T> predicate);

    NEXT or(Predicate<T> predicate);

    // interface Builder<T, NEXT> {
    //     BooleanExpression build(SubPredicateHeaderCombinable<T, SubPredicateAssembler<T, NEXT>> builder);
    // }

}
