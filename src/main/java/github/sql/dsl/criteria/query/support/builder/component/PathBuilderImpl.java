package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.builder.combination.ComparablePredicateTester;
import github.sql.dsl.criteria.query.builder.combination.NumberPredicateTester;
import github.sql.dsl.criteria.query.builder.combination.PredicateTester;
import github.sql.dsl.criteria.query.builder.combination.StringPredicateTester;
import github.sql.dsl.criteria.query.expression.Operator;
import github.sql.dsl.criteria.query.expression.path.AttributePath;
import github.sql.dsl.criteria.query.expression.path.Entity;
import github.sql.dsl.criteria.query.expression.path.PathBuilder;
import github.sql.dsl.criteria.query.expression.path.attribute.*;

import java.util.function.Function;

public class PathBuilderImpl<T, U, NEXT> implements PathBuilder<T, U, NEXT> {

    private final AttributePath<T, U> path;
    private final Operator combined;
    protected final boolean negate;
    private final Function<SubPredicate, NEXT> mapper;

    public PathBuilderImpl(AttributePath<T, U> path,
                           Operator combined,
                           boolean negate,
                           Function<SubPredicate, NEXT> mapper) {
        this.path = path;
        this.combined = combined;
        this.negate = negate;
        this.mapper = mapper;
    }

    @Override
    public <R extends Entity> PathBuilderImpl<T, R, NEXT> map(EntityAttribute<U, R> attribute) {
        AttributePath<T, R> strings = path.mapTo(attribute);
        return new PathBuilderImpl<>(strings, combined, negate, mapper);
    }

    @Override
    public <R extends Number & Comparable<?>> NumberPredicateTester<T, R, NEXT> map(NumberAttribute<U, R> attribute) {
        AttributePath<T, R> strings = path.mapTo(attribute);
        return new NumberPredicateTesterImpl<>(strings, combined, negate, mapper);
    }

    @Override
    public <R extends Comparable<?>> ComparablePredicateTester<T, R, NEXT> map(ComparableAttribute<U, R> attribute) {
        AttributePath<T, R> strings = path.mapTo(attribute);
        return new ComparablePredicateTesterImpl<>(strings, combined, negate, mapper);

    }

    @Override
    public <R extends Comparable<?>> PredicateTester<T, R, NEXT> map(Attribute<U, R> attribute) {
        AttributePath<T, R> strings = path.mapTo(attribute);
        return new PredicateTesterImpl<>(strings, combined, negate, mapper);
    }

    @Override
    public StringPredicateTester<T, NEXT> map(StringAttribute<U> attribute) {
        AttributePath<T, String> strings = path.mapTo(attribute);
        return new StringPredicateTesterImpl<>(strings, combined, negate, mapper);
    }
}
