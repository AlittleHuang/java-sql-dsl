package github.sql.dsl.criteria.query.support.builder.query;

import github.sql.dsl.criteria.query.builder.PredicateCombinable;
import github.sql.dsl.criteria.query.builder.combination.*;
import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.Predicate;
import github.sql.dsl.criteria.query.expression.path.Entity;
import github.sql.dsl.criteria.query.expression.path.PathBuilder;
import github.sql.dsl.criteria.query.expression.path.attribute.*;
import github.sql.dsl.criteria.query.support.builder.criteria.PredicateCombinableImpl;
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
    public <U> PredicateBuilder<T, U, NEXT> where(Attribute<T, U> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public <U extends Number> NumberPredicateBuilder<T, U, NEXT>
    where(NumberAttribute<T, U> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public <U extends Date> ComparablePredicateBuilder<T, U, NEXT>
    where(ComparableAttribute<T, U> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public StringPredicateBuilder<T, NEXT> where(StringAttribute<T> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public <U extends Entity> PathBuilder<T, U, NEXT> whereNot(EntityAttribute<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public <U> PredicateBuilder<T, U, NEXT> whereNot(Attribute<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public <U extends Number> NumberPredicateBuilder<T, U, NEXT> whereNot(NumberAttribute<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public <U extends Date> ComparablePredicateBuilder<T, U, NEXT> whereNot(ComparableAttribute<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public StringPredicateBuilder<T, NEXT> whereNot(StringAttribute<T> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public NEXT Where(PredicateCombinable.Builder<T, NEXT> builder) {
        return getBuilder().andAppend(builder);
    }

    @Override
    public NEXT where(Predicate<T> predicate) {
        return getBuilder().and(predicate);
    }
}
