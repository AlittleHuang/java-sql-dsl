package github.sql.dsl.query.api.suport.builder.component;

import github.sql.dsl.query.api.builder.combination.ComparablePredicateBuilder;
import github.sql.dsl.query.api.builder.combination.NumberPredicateBuilder;
import github.sql.dsl.query.api.builder.combination.PredicateBuilder;
import github.sql.dsl.query.api.builder.combination.StringPredicateBuilder;
import github.sql.dsl.query.api.expression.Operator;
import github.sql.dsl.query.api.expression.path.AttributePath;
import github.sql.dsl.query.api.expression.path.Entity;
import github.sql.dsl.query.api.expression.path.PathBuilder;
import github.sql.dsl.query.api.expression.path.attribute.*;

import java.util.Date;
import java.util.function.Function;

public class PathBuilderSupport<T, U, NEXT> implements PathBuilder<T, U, NEXT> {

    private final AttributePath<T, U> path;
    private final Operator combined;
    protected final boolean negate;
    private final Function<SubPredicate, NEXT> mapper;

    public PathBuilderSupport(AttributePath<T, U> path,
                              Operator combined,
                              boolean negate,
                              Function<SubPredicate, NEXT> mapper) {
        this.path = path;
        this.combined = combined;
        this.negate = negate;
        this.mapper = mapper;
    }

    @Override
    public <R extends Entity> PathBuilderSupport<T, R, NEXT> map(EntityAttribute<U, R> attribute) {
        AttributePath<T, R> strings = path.mapTo(attribute);
        return new PathBuilderSupport<>(strings, combined, negate, mapper);
    }

    @Override
    public <R extends Number> NumberPredicateBuilder<T, R, NEXT> map(NumberAttribute<U, R> attribute) {
        AttributePath<T, R> strings = path.mapTo(attribute);
        return new NumberPredicateBuilderSupport<>(strings, combined, negate, mapper);
    }

    @Override
    public <R extends Date> ComparablePredicateBuilder<T, R, NEXT> map(ComparableAttribute<U, R> attribute) {
        AttributePath<T, R> strings = path.mapTo(attribute);
        return new ComparablePredicateBuilderSupport<>(strings, combined, negate, mapper);

    }

    @Override
    public <R extends Date> PredicateBuilder<T, R, NEXT> map(Attribute<U, R> attribute) {
        AttributePath<T, R> strings = path.mapTo(attribute);
        return new PredicateBuilderSupport<>(strings, combined, negate, mapper);
    }

    @Override
    public StringPredicateBuilder<T, NEXT> map(StringAttribute<U> attribute) {
        AttributePath<T, String> strings = path.mapTo(attribute);
        return new StringPredicateBuilderSupport<>(strings, combined, negate, mapper);
    }
}
