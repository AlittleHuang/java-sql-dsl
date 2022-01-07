package github.sql.dsl.query.suport.builder.component;

import github.sql.dsl.query.api.expression.*;
import github.sql.dsl.query.api.expression.path.AttributePath;
import github.sql.dsl.query.api.expression.path.Entity;
import github.sql.dsl.query.api.expression.path.PathBuilder;
import github.sql.dsl.query.api.expression.path.bridge.*;

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
    public <R extends Number> NumberExpressionBuilder<T, R, NEXT> map(NumberAttribute<U, R> attribute) {
        AttributePath<T, R> strings = path.mapTo(attribute);
        return new NumberExpressionBuilderImpl<>(strings, combined, negate, mapper);
    }

    @Override
    public <R extends Date> ComparableExpressionBuilder<T, R, NEXT> map(ComparableAttribute<U, R> attribute) {
        AttributePath<T, R> strings = path.mapTo(attribute);
        return new ComparableExpressionBuilderImpl<>(strings, combined, negate, mapper);

    }

    @Override
    public <R extends Date> ExpressionBuilder<T, R, NEXT> map(Attribute<U, R> attribute) {
        AttributePath<T, R> strings = path.mapTo(attribute);
        return new ExpressionBuilderImpl<>(strings, combined, negate, mapper);
    }

    @Override
    public StringExpressionBuilder<T, NEXT> map(StringAttribute<U> attribute) {
        AttributePath<T, String> strings = path.mapTo(attribute);
        return new StringExpressionBuilderImpl<>(strings, combined, negate, mapper);
    }
}
