package github.sql.dsl.query.suport.builder.criteria;

import github.sql.dsl.query.api.builder.PredicateCombinable;
import github.sql.dsl.query.api.expression.*;
import github.sql.dsl.query.api.expression.path.BridgePath;
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
    public <R extends Entity> PathBuilder<T, R, NEXT> and(EntityAttributeBridge<T, R> attribute) {
        return new PathBuilderImpl<>(BridgePath.exchange(attribute), Operator.AND, false, this::mapperNext);
    }

    @Override
    public <R extends Entity> PathBuilder<T, R, NEXT> or(EntityAttributeBridge<T, R> attribute) {
        return new PathBuilderImpl<>(BridgePath.exchange(attribute), Operator.OR, false, this::mapperNext);
    }

    @Override
    public <R extends Entity> PathBuilder<T, R, NEXT> andNot(EntityAttributeBridge<T, R> attribute) {
        return new PathBuilderImpl<>(BridgePath.exchange(attribute), Operator.AND, true, this::mapperNext);
    }

    @Override
    public <R extends Entity> PathBuilder<T, R, NEXT> orNot(EntityAttributeBridge<T, R> attribute) {
        return new PathBuilderImpl<>(BridgePath.exchange(attribute), Operator.OR, true, this::mapperNext);
    }

    @Override
    public <R> ExpressionBuilder<T, R, NEXT> and(AttributeBridge<T, R> attribute) {
        return new ExpressionBuilderImpl<>(BridgePath.exchange(attribute), Operator.AND, false, this::mapperNext);
    }

    @Override
    public <R> ExpressionBuilder<T, R, NEXT> or(AttributeBridge<T, R> attribute) {
        return new ExpressionBuilderImpl<>(BridgePath.exchange(attribute), Operator.OR, false, this::mapperNext);
    }

    @Override
    public <R> ExpressionBuilder<T, R, NEXT> andNot(AttributeBridge<T, R> attribute) {
        return new ExpressionBuilderImpl<>(BridgePath.exchange(attribute), Operator.AND, true, this::mapperNext);
    }

    @Override
    public <R> ExpressionBuilder<T, R, NEXT> orNot(AttributeBridge<T, R> attribute) {
        return new ExpressionBuilderImpl<>(BridgePath.exchange(attribute), Operator.OR, true, this::mapperNext);
    }

    @Override
    public <R extends Number> NumberExpressionBuilder<T, R, NEXT> and(NumberAttributeBridge<T, R> attribute) {
        return new NumberExpressionBuilderImpl<>(BridgePath.exchange(attribute), Operator.AND, false, this::mapperNext);
    }

    @Override
    public <R extends Number> NumberExpressionBuilder<T, R, NEXT> or(NumberAttributeBridge<T, R> attribute) {
        return new NumberExpressionBuilderImpl<>(BridgePath.exchange(attribute), Operator.OR, false, this::mapperNext);
    }

    @Override
    public <R extends Number> NumberExpressionBuilder<T, R, NEXT> andNot(NumberAttributeBridge<T, R> attribute) {
        return new NumberExpressionBuilderImpl<>(BridgePath.exchange(attribute), Operator.AND, true, this::mapperNext);
    }

    @Override
    public <R extends Number> NumberExpressionBuilder<T, R, NEXT> orNot(NumberAttributeBridge<T, R> attribute) {
        return new NumberExpressionBuilderImpl<>(BridgePath.exchange(attribute), Operator.OR, true, this::mapperNext);
    }

    @Override
    public <R extends Date> ComparableExpressionBuilder<T, R, NEXT> and(ComparableAttributeBridge<T, R> attribute) {
        return new ComparableExpressionBuilderImpl<>(BridgePath.exchange(attribute), Operator.AND, false, this::mapperNext);
    }

    @Override
    public <R extends Date> ComparableExpressionBuilder<T, R, NEXT> or(ComparableAttributeBridge<T, R> attribute) {
        return new ComparableExpressionBuilderImpl<>(BridgePath.exchange(attribute), Operator.OR, false, this::mapperNext);
    }

    @Override
    public <R extends Date> ComparableExpressionBuilder<T, R, NEXT> andNot(ComparableAttributeBridge<T, R> attribute) {
        return new ComparableExpressionBuilderImpl<>(BridgePath.exchange(attribute), Operator.AND, true, this::mapperNext);
    }

    @Override
    public <R extends Date> ComparableExpressionBuilder<T, R, NEXT> orNot(ComparableAttributeBridge<T, R> attribute) {
        return new ComparableExpressionBuilderImpl<>(BridgePath.exchange(attribute), Operator.OR, true, this::mapperNext);
    }

    @Override
    public StringExpressionBuilder<T, NEXT> and(StringAttributeBridge<T> attribute) {
        return new StringExpressionBuilderImpl<>(BridgePath.exchange(attribute), Operator.AND, false, this::mapperNext);
    }

    @Override
    public StringExpressionBuilder<T, NEXT> or(StringAttributeBridge<T> attribute) {
        return new StringExpressionBuilderImpl<>(BridgePath.exchange(attribute), Operator.OR, false, this::mapperNext);
    }

    @Override
    public StringExpressionBuilder<T, NEXT> andNot(StringAttributeBridge<T> attribute) {
        return new StringExpressionBuilderImpl<>(BridgePath.exchange(attribute), Operator.AND, true, this::mapperNext);
    }

    @Override
    public StringExpressionBuilder<T, NEXT> orNot(StringAttributeBridge<T> attribute) {
        return new StringExpressionBuilderImpl<>(BridgePath.exchange(attribute), Operator.OR, true, this::mapperNext);
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
