package github.sql.dsl.query.suport.builder.query;

import github.sql.dsl.query.api.expression.*;
import github.sql.dsl.query.api.expression.path.Entity;
import github.sql.dsl.query.api.expression.path.PathBuilder;
import github.sql.dsl.query.api.expression.path.bridge.*;
import github.sql.dsl.query.api.query.SubPredicateCombinable;
import github.sql.dsl.query.api.query.SubPredicateHeaderCombinable;
import github.sql.dsl.query.suport.builder.criteria.PredicateCombinableImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.function.Function;

public class SubPredicateHeaderCombinableImpl<T, NEXT> implements SubPredicateHeaderCombinable<T, NEXT> {

    private final Function<Expression<Boolean>, NEXT> mapper;

    public SubPredicateHeaderCombinableImpl(Function<Expression<Boolean>, NEXT> mapper) {
        this.mapper = mapper;
    }

    @NotNull
    private PredicateCombinableImpl<T, NEXT> getBuilder() {
        return new PredicateCombinableImpl<>(null, mapper);
    }


    @Override
    public <U extends Entity> PathBuilder<T, U, NEXT> get(EntityAttributeBridge<T, U> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public <U> ExpressionBuilder<T, U, NEXT> get(AttributeBridge<T, U> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public <U extends Number> NumberExpressionBuilder<T, U, NEXT>
    get(NumberAttributeBridge<T, U> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public <U extends Date> ComparableExpressionBuilder<T, U, NEXT>
    get(ComparableAttributeBridge<T, U> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public StringExpressionBuilder<T, NEXT> get(StringAttributeBridge<T> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public <U extends Entity> PathBuilder<T, U, NEXT> not(EntityAttributeBridge<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public <U> ExpressionBuilder<T, U, NEXT> not(AttributeBridge<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public <U extends Number> NumberExpressionBuilder<T, U, NEXT> not(NumberAttributeBridge<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public <U extends Date> ComparableExpressionBuilder<T, U, NEXT> not(ComparableAttributeBridge<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public StringExpressionBuilder<T, NEXT> not(StringAttributeBridge<T> attribute) {
        return getBuilder().andNot(attribute);
    }


}
