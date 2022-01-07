package github.sql.dsl.query.suport.builder.criteria;

import github.sql.dsl.query.api.builder.PredicateCombinable;
import github.sql.dsl.query.api.expression.*;
import github.sql.dsl.query.api.expression.path.AttributePath;
import github.sql.dsl.query.api.expression.path.Entity;
import github.sql.dsl.query.api.expression.path.PathBuilder;
import github.sql.dsl.query.api.expression.path.bridge.*;
import github.sql.dsl.query.api.query.SubPredicateCombinable;
import github.sql.dsl.query.api.query.SubPredicateHeaderCombinable;
import github.sql.dsl.query.suport.builder.component.*;
import github.sql.dsl.query.suport.builder.query.SubPredicateHeaderCombinableImpl;

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
    public <R> ExpressionBuilder<T, R, NEXT> and(Attribute<T, R> attribute) {
        return new ExpressionBuilderImpl<>(AttributePath.exchange(attribute), Operator.AND, false, this::mapperNext);
    }

    @Override
    public <R> ExpressionBuilder<T, R, NEXT> or(Attribute<T, R> attribute) {
        return new ExpressionBuilderImpl<>(AttributePath.exchange(attribute), Operator.OR, false, this::mapperNext);
    }

    @Override
    public <R> ExpressionBuilder<T, R, NEXT> andNot(Attribute<T, R> attribute) {
        return new ExpressionBuilderImpl<>(AttributePath.exchange(attribute), Operator.AND, true, this::mapperNext);
    }

    @Override
    public <R> ExpressionBuilder<T, R, NEXT> orNot(Attribute<T, R> attribute) {
        return new ExpressionBuilderImpl<>(AttributePath.exchange(attribute), Operator.OR, true, this::mapperNext);
    }

    @Override
    public <R extends Number> NumberExpressionBuilder<T, R, NEXT> and(NumberAttribute<T, R> attribute) {
        return new NumberExpressionBuilderImpl<>(AttributePath.exchange(attribute), Operator.AND, false, this::mapperNext);
    }

    @Override
    public <R extends Number> NumberExpressionBuilder<T, R, NEXT> or(NumberAttribute<T, R> attribute) {
        return new NumberExpressionBuilderImpl<>(AttributePath.exchange(attribute), Operator.OR, false, this::mapperNext);
    }

    @Override
    public <R extends Number> NumberExpressionBuilder<T, R, NEXT> andNot(NumberAttribute<T, R> attribute) {
        return new NumberExpressionBuilderImpl<>(AttributePath.exchange(attribute), Operator.AND, true, this::mapperNext);
    }

    @Override
    public <R extends Number> NumberExpressionBuilder<T, R, NEXT> orNot(NumberAttribute<T, R> attribute) {
        return new NumberExpressionBuilderImpl<>(AttributePath.exchange(attribute), Operator.OR, true, this::mapperNext);
    }

    @Override
    public <R extends Date> ComparableExpressionBuilder<T, R, NEXT> and(ComparableAttribute<T, R> attribute) {
        return new ComparableExpressionBuilderImpl<>(AttributePath.exchange(attribute), Operator.AND, false, this::mapperNext);
    }

    @Override
    public <R extends Date> ComparableExpressionBuilder<T, R, NEXT> or(ComparableAttribute<T, R> attribute) {
        return new ComparableExpressionBuilderImpl<>(AttributePath.exchange(attribute), Operator.OR, false, this::mapperNext);
    }

    @Override
    public <R extends Date> ComparableExpressionBuilder<T, R, NEXT> andNot(ComparableAttribute<T, R> attribute) {
        return new ComparableExpressionBuilderImpl<>(AttributePath.exchange(attribute), Operator.AND, true, this::mapperNext);
    }

    @Override
    public <R extends Date> ComparableExpressionBuilder<T, R, NEXT> orNot(ComparableAttribute<T, R> attribute) {
        return new ComparableExpressionBuilderImpl<>(AttributePath.exchange(attribute), Operator.OR, true, this::mapperNext);
    }

    @Override
    public StringExpressionBuilder<T, NEXT> and(StringAttribute<T> attribute) {
        return new StringExpressionBuilderImpl<>(AttributePath.exchange(attribute), Operator.AND, false, this::mapperNext);
    }

    @Override
    public StringExpressionBuilder<T, NEXT> or(StringAttribute<T> attribute) {
        return new StringExpressionBuilderImpl<>(AttributePath.exchange(attribute), Operator.OR, false, this::mapperNext);
    }

    @Override
    public StringExpressionBuilder<T, NEXT> andNot(StringAttribute<T> attribute) {
        return new StringExpressionBuilderImpl<>(AttributePath.exchange(attribute), Operator.AND, true, this::mapperNext);
    }

    @Override
    public StringExpressionBuilder<T, NEXT> orNot(StringAttribute<T> attribute) {
        return new StringExpressionBuilderImpl<>(AttributePath.exchange(attribute), Operator.OR, true, this::mapperNext);
    }

    @Override
    public NEXT And(Function<SubPredicateHeaderCombinable<T, SubPredicateCombinable<T, NEXT>>, BooleanExpression> builder) {
        SubPredicateHeaderCombinableImpl<T, SubPredicateCombinable<T, NEXT>> r =
                new SubPredicateHeaderCombinableImpl<>(SubPredicateCombinableImpl::new);
        Expression<Boolean> then = builder.apply(r);
        then = expression == null
                ? then
                : expression.then(Operator.AND, then);
        return this.mapper.apply(then);
    }

    @Override
    public NEXT Or(Function<SubPredicateHeaderCombinable<T, SubPredicateCombinable<T, NEXT>>, BooleanExpression> builder) {
        SubPredicateHeaderCombinableImpl<T, SubPredicateCombinable<T, NEXT>> r =
                new SubPredicateHeaderCombinableImpl<>(SubPredicateCombinableImpl::new);
        Expression<Boolean> then = builder.apply(r);
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
