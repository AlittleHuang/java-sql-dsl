package github.sql.dsl.criteria.query.support.builder.criteria;

import github.sql.dsl.criteria.query.builder.PredicateAssembler;
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

public class PredicateAssemblerImpl<T, NEXT> implements PredicateAssembler<T, NEXT> {

    protected final SubPredicateArray expression;
    protected final Function<Expression<Boolean>, NEXT> mapper;

    public PredicateAssemblerImpl(Expression<Boolean> expression,
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
    public <R> PredicateTester<T, R, NEXT> and(Attribute<T, R> attribute) {
        return new PredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.AND, false, this::mapperNext);
    }

    @Override
    public <R> PredicateTester<T, R, NEXT> or(Attribute<T, R> attribute) {
        return new PredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.OR, false, this::mapperNext);
    }

    @Override
    public <R> PredicateTester<T, R, NEXT> andNot(Attribute<T, R> attribute) {
        return new PredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.AND, true, this::mapperNext);
    }

    @Override
    public <R> PredicateTester<T, R, NEXT> orNot(Attribute<T, R> attribute) {
        return new PredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.OR, true, this::mapperNext);
    }

    @Override
    public <R extends Number> NumberPredicateTester<T, R, NEXT> and(NumberAttribute<T, R> attribute) {
        return new NumberPredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.AND, false, this::mapperNext);
    }

    @Override
    public <R extends Number> NumberPredicateTester<T, R, NEXT> or(NumberAttribute<T, R> attribute) {
        return new NumberPredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.OR, false, this::mapperNext);
    }

    @Override
    public <R extends Number> NumberPredicateTester<T, R, NEXT> andNot(NumberAttribute<T, R> attribute) {
        return new NumberPredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.AND, true, this::mapperNext);
    }

    @Override
    public <R extends Number> NumberPredicateTester<T, R, NEXT> orNot(NumberAttribute<T, R> attribute) {
        return new NumberPredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.OR, true, this::mapperNext);
    }

    @Override
    public <R extends Date> ComparablePredicateTester<T, R, NEXT> and(ComparableAttribute<T, R> attribute) {
        return new ComparablePredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.AND, false, this::mapperNext);
    }

    @Override
    public <R extends Date> ComparablePredicateTester<T, R, NEXT> or(ComparableAttribute<T, R> attribute) {
        return new ComparablePredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.OR, false, this::mapperNext);
    }

    @Override
    public <R extends Date> ComparablePredicateTester<T, R, NEXT> andNot(ComparableAttribute<T, R> attribute) {
        return new ComparablePredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.AND, true, this::mapperNext);
    }

    @Override
    public <R extends Date> ComparablePredicateTester<T, R, NEXT> orNot(ComparableAttribute<T, R> attribute) {
        return new ComparablePredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.OR, true, this::mapperNext);
    }

    @Override
    public StringPredicateTester<T, NEXT> and(StringAttribute<T> attribute) {
        return new StringPredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.AND, false, this::mapperNext);
    }

    @Override
    public StringPredicateTester<T, NEXT> or(StringAttribute<T> attribute) {
        return new StringPredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.OR, false, this::mapperNext);
    }

    @Override
    public StringPredicateTester<T, NEXT> andNot(StringAttribute<T> attribute) {
        return new StringPredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.AND, true, this::mapperNext);
    }

    @Override
    public StringPredicateTester<T, NEXT> orNot(StringAttribute<T> attribute) {
        return new StringPredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.OR, true, this::mapperNext);
    }

    @Override
    public NEXT and(Predicate<T> predicate) {
        SubPredicate subPredicate = new SubPredicate(predicate, Operator.AND, false);
        return mapperNext(subPredicate);
    }

    @Override
    public NEXT or(Predicate<T> predicate) {
        SubPredicate subPredicate = new SubPredicate(predicate, Operator.OR, false);
        return mapperNext(subPredicate);
    }

    @Override
    public NEXT andAppend(Builder<T, NEXT> builder) {
        SubPredicateHeaderCombinableImpl<T, SubPredicateAssembler<T, NEXT>> r =
                new SubPredicateHeaderCombinableImpl<>(SubPredicateAssemblerImpl::new);
        Expression<Boolean> then = builder.build(r);
        then = expression == null
                ? then
                : expression.then(Operator.AND, then);
        return this.mapper.apply(then);
    }

    @Override
    public NEXT orAppend(Builder<T, NEXT> builder) {
        SubPredicateHeaderCombinableImpl<T, SubPredicateAssembler<T, NEXT>> r =
                new SubPredicateHeaderCombinableImpl<>(SubPredicateAssemblerImpl::new);
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
