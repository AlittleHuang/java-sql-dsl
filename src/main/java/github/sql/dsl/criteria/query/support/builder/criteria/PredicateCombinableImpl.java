package github.sql.dsl.criteria.query.support.builder.criteria;

import github.sql.dsl.criteria.query.builder.PredicateCombinable;
import github.sql.dsl.criteria.query.builder.combination.*;
import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.Operator;
import github.sql.dsl.criteria.query.expression.Predicate;
import github.sql.dsl.criteria.query.expression.path.AttributePath;
import github.sql.dsl.criteria.query.expression.path.Entity;
import github.sql.dsl.criteria.query.expression.path.PathBuilder;
import github.sql.dsl.criteria.query.expression.path.attribute.*;
import github.sql.dsl.criteria.query.support.builder.component.*;
import github.sql.dsl.criteria.query.support.builder.query.SubPredicateHeaderCombinableImpl;

import java.util.Date;
import java.util.function.Function;

public class PredicateCombinableImpl<T, NEXT> implements PredicateCombinable<T, NEXT> {

    protected final SubPredicateArray expression;
    protected final Function<Expression<Boolean>, NEXT> mapper;

    public PredicateCombinableImpl(Expression<Boolean> expression,
                                   Function<Expression<Boolean>, NEXT> mapper) {
        this.expression = SubPredicateArray.fromExpression(expression);
        this.mapper = mapper;
    }

    @Override
    public <R extends Entity> PathBuilder<T, R, NEXT> and(EntityAttribute<T, R> attribute) {
        return new PathBuilderImpl<>(AttributePath.exchange(attribute), Operator.AND, false, this::mapperNext);
    }

    @Override
    public <R extends Entity> PathBuilder<T, R, NEXT> or(EntityAttribute<T, R> attribute) {
        return new PathBuilderImpl<>(AttributePath.exchange(attribute), Operator.OR, false, this::mapperNext);
    }

    @Override
    public <R extends Entity> PathBuilder<T, R, NEXT> andNot(EntityAttribute<T, R> attribute) {
        return new PathBuilderImpl<>(AttributePath.exchange(attribute), Operator.AND, true, this::mapperNext);
    }

    @Override
    public <R extends Entity> PathBuilder<T, R, NEXT> orNot(EntityAttribute<T, R> attribute) {
        return new PathBuilderImpl<>(AttributePath.exchange(attribute), Operator.OR, true, this::mapperNext);
    }

    @Override
    public <R> PredicateBuilder<T, R, NEXT> and(Attribute<T, R> attribute) {
        return new PredicateBuilderImpl<>(AttributePath.exchange(attribute), Operator.AND, false, this::mapperNext);
    }

    @Override
    public <R> PredicateBuilder<T, R, NEXT> or(Attribute<T, R> attribute) {
        return new PredicateBuilderImpl<>(AttributePath.exchange(attribute), Operator.OR, false, this::mapperNext);
    }

    @Override
    public <R> PredicateBuilder<T, R, NEXT> andNot(Attribute<T, R> attribute) {
        return new PredicateBuilderImpl<>(AttributePath.exchange(attribute), Operator.AND, true, this::mapperNext);
    }

    @Override
    public <R> PredicateBuilder<T, R, NEXT> orNot(Attribute<T, R> attribute) {
        return new PredicateBuilderImpl<>(AttributePath.exchange(attribute), Operator.OR, true, this::mapperNext);
    }

    @Override
    public <R extends Number> NumberPredicateBuilder<T, R, NEXT> and(NumberAttribute<T, R> attribute) {
        return new NumberPredicateBuilderImpl<>(AttributePath.exchange(attribute), Operator.AND, false, this::mapperNext);
    }

    @Override
    public <R extends Number> NumberPredicateBuilder<T, R, NEXT> or(NumberAttribute<T, R> attribute) {
        return new NumberPredicateBuilderImpl<>(AttributePath.exchange(attribute), Operator.OR, false, this::mapperNext);
    }

    @Override
    public <R extends Number> NumberPredicateBuilder<T, R, NEXT> andNot(NumberAttribute<T, R> attribute) {
        return new NumberPredicateBuilderImpl<>(AttributePath.exchange(attribute), Operator.AND, true, this::mapperNext);
    }

    @Override
    public <R extends Number> NumberPredicateBuilder<T, R, NEXT> orNot(NumberAttribute<T, R> attribute) {
        return new NumberPredicateBuilderImpl<>(AttributePath.exchange(attribute), Operator.OR, true, this::mapperNext);
    }

    @Override
    public <R extends Date> ComparablePredicateBuilder<T, R, NEXT> and(ComparableAttribute<T, R> attribute) {
        return new ComparablePredicateBuilderImpl<>(AttributePath.exchange(attribute), Operator.AND, false, this::mapperNext);
    }

    @Override
    public <R extends Date> ComparablePredicateBuilder<T, R, NEXT> or(ComparableAttribute<T, R> attribute) {
        return new ComparablePredicateBuilderImpl<>(AttributePath.exchange(attribute), Operator.OR, false, this::mapperNext);
    }

    @Override
    public <R extends Date> ComparablePredicateBuilder<T, R, NEXT> andNot(ComparableAttribute<T, R> attribute) {
        return new ComparablePredicateBuilderImpl<>(AttributePath.exchange(attribute), Operator.AND, true, this::mapperNext);
    }

    @Override
    public <R extends Date> ComparablePredicateBuilder<T, R, NEXT> orNot(ComparableAttribute<T, R> attribute) {
        return new ComparablePredicateBuilderImpl<>(AttributePath.exchange(attribute), Operator.OR, true, this::mapperNext);
    }

    @Override
    public StringPredicateBuilder<T, NEXT> and(StringAttribute<T> attribute) {
        return new StringPredicateBuilderImpl<>(AttributePath.exchange(attribute), Operator.AND, false, this::mapperNext);
    }

    @Override
    public StringPredicateBuilder<T, NEXT> or(StringAttribute<T> attribute) {
        return new StringPredicateBuilderImpl<>(AttributePath.exchange(attribute), Operator.OR, false, this::mapperNext);
    }

    @Override
    public StringPredicateBuilder<T, NEXT> andNot(StringAttribute<T> attribute) {
        return new StringPredicateBuilderImpl<>(AttributePath.exchange(attribute), Operator.AND, true, this::mapperNext);
    }

    @Override
    public StringPredicateBuilder<T, NEXT> orNot(StringAttribute<T> attribute) {
        return new StringPredicateBuilderImpl<>(AttributePath.exchange(attribute), Operator.OR, true, this::mapperNext);
    }

    @Override
    public NEXT and(Predicate<T> predicate) {
        //noinspection unchecked
        SubPredicate subPredicate = new SubPredicate((Expression<Boolean>) predicate, Operator.AND, false);
        return mapperNext(subPredicate);
    }

    @Override
    public NEXT or(Predicate<T> predicate) {
        //noinspection unchecked
        SubPredicate subPredicate = new SubPredicate((Expression<Boolean>) predicate, Operator.OR, false);
        return mapperNext(subPredicate);
    }

    @Override
    public NEXT andAppend(Builder<T, NEXT> builder) {
        SubPredicateHeaderCombinableImpl<T, SubPredicateCombinable<T, NEXT>> r =
                new SubPredicateHeaderCombinableImpl<>(SubPredicateCombinableImpl::new);
        Expression<Boolean> then = builder.build(r);
        then = expression == null
                ? then
                : expression.then(Operator.AND, then);
        return this.mapper.apply(then);
    }

    @Override
    public NEXT orAppend(Builder<T, NEXT> builder) {
        SubPredicateHeaderCombinableImpl<T, SubPredicateCombinable<T, NEXT>> r =
                new SubPredicateHeaderCombinableImpl<>(SubPredicateCombinableImpl::new);
        Expression<Boolean> then = builder.build(r);
        then = expression == null
                ? then
                : expression.then(Operator.OR, then);
        return mapper.apply(then);
    }

    protected NEXT mapperNext(SubPredicate subPredicate) {
        Expression<Boolean> then = getBooleanExpression(subPredicate);
        return next(mapper.apply(then));
    }

    protected NEXT next(NEXT next) {
        return next;
    }

    private Expression<Boolean> getBooleanExpression(SubPredicate subPredicate) {
        Expression<Boolean> expression = subPredicate.getExpression();
        if (subPredicate.isNegate()) {
            expression = expression.then(Operator.NOT);
        }
        return this.expression == null
                ? expression
                : this.expression.then(subPredicate.getCombined(), expression);
    }


}
