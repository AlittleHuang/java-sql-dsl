package github.sql.dsl.query.suport.builder.query;

import github.sql.dsl.query.api.expression.*;
import github.sql.dsl.query.api.expression.path.Entity;
import github.sql.dsl.query.api.expression.path.PathBuilder;
import github.sql.dsl.query.api.expression.path.bridge.*;
import github.sql.dsl.query.api.query.SubPredicateCombinable;
import github.sql.dsl.query.api.query.SubPredicateHeaderCombinable;
import github.sql.dsl.query.api.query.Whereable;
import github.sql.dsl.query.suport.builder.criteria.PredicateCombinableImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.function.Function;

public class WhereableImpl<T, NEXT> implements Whereable<T, NEXT> {

    private final Function<Expression<Boolean>, NEXT> mapper;

    public WhereableImpl(Function<Expression<Boolean>, NEXT> mapper) {
        this.mapper = mapper;
    }

    @NotNull
    private PredicateCombinableImpl<T, NEXT> getBuilder() {
        return new PredicateCombinableImpl<>(null, mapper);
    }


    @Override
    public <U extends Entity> PathBuilder<T, U, NEXT> where(EntityAttribute<T, U> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public <U> ExpressionBuilder<T, U, NEXT> where(Attribute<T, U> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public <U extends Number> NumberExpressionBuilder<T, U, NEXT>
    where(NumberAttribute<T, U> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public <U extends Date> ComparableExpressionBuilder<T, U, NEXT>
    where(ComparableAttribute<T, U> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public StringExpressionBuilder<T, NEXT> where(StringAttribute<T> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public <U extends Entity> PathBuilder<T, U, NEXT> whereNot(EntityAttribute<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public <U> ExpressionBuilder<T, U, NEXT> whereNot(Attribute<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public <U extends Number> NumberExpressionBuilder<T, U, NEXT> whereNot(NumberAttribute<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public <U extends Date> ComparableExpressionBuilder<T, U, NEXT> whereNot(ComparableAttribute<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public StringExpressionBuilder<T, NEXT> whereNot(StringAttribute<T> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public NEXT Where(Function<SubPredicateHeaderCombinable<T, SubPredicateCombinable<T, NEXT>>, BooleanExpression> builder) {
        return getBuilder().And(builder);
    }


}
