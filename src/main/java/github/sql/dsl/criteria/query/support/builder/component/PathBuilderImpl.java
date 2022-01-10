package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.builder.combination.ComparablePredicateBuilder;
import github.sql.dsl.criteria.query.builder.combination.NumberPredicateBuilder;
import github.sql.dsl.criteria.query.builder.combination.PredicateBuilder;
import github.sql.dsl.criteria.query.builder.combination.StringPredicateBuilder;
import github.sql.dsl.criteria.query.expression.Operator;
import github.sql.dsl.criteria.query.expression.path.AttributePath;
import github.sql.dsl.criteria.query.expression.path.Entity;
import github.sql.dsl.criteria.query.expression.path.PathBuilder;
import github.sql.dsl.criteria.query.expression.path.attribute.*;

import java.util.Date;
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
    public <R extends Number> NumberPredicateBuilder<T, R, NEXT> map(NumberAttribute<U, R> attribute) {
        AttributePath<T, R> strings = path.mapTo(attribute);
        return new NumberPredicateBuilderImpl<>(strings, combined, negate, mapper);
    }

    @Override
    public <R extends Date> ComparablePredicateBuilder<T, R, NEXT> map(ComparableAttribute<U, R> attribute) {
        AttributePath<T, R> strings = path.mapTo(attribute);
        return new ComparablePredicateBuilderImpl<>(strings, combined, negate, mapper);

    }

    @Override
    public <R extends Date> PredicateBuilder<T, R, NEXT> map(Attribute<U, R> attribute) {
        AttributePath<T, R> strings = path.mapTo(attribute);
        return new PredicateBuilderImpl<>(strings, combined, negate, mapper);
    }

    @Override
    public StringPredicateBuilder<T, NEXT> map(StringAttribute<U> attribute) {
        AttributePath<T, String> strings = path.mapTo(attribute);
        return new StringPredicateBuilderImpl<>(strings, combined, negate, mapper);
    }
}
