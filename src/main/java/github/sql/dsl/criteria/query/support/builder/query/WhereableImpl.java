package github.sql.dsl.criteria.query.support.builder.query;

import github.sql.dsl.criteria.query.builder.combination.*;
import github.sql.dsl.criteria.query.expression.SqlExpression;
import github.sql.dsl.criteria.query.expression.Predicate;
import github.sql.dsl.criteria.query.expression.path.Entity;
import github.sql.dsl.criteria.query.expression.path.PathBuilder;
import github.sql.dsl.criteria.query.expression.path.attribute.*;
import github.sql.dsl.criteria.query.support.builder.criteria.PredicateAssemblerImpl;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class WhereableImpl<T, NEXT> implements Whereable<T, NEXT> {

    private final Function<SqlExpression<Boolean>, NEXT> mapper;

    public WhereableImpl(Function<SqlExpression<Boolean>, NEXT> mapper) {
        this.mapper = mapper;
    }

    @NotNull
    private PredicateAssemblerImpl<T, NEXT> getBuilder() {
        return new PredicateAssemblerImpl<>(null, mapper);
    }


    @Override
    public <U extends Entity> PathBuilder<T, U, NEXT> where(EntityAttribute<T, U> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public <U> PredicateTester<T, U, NEXT> where(Attribute<T, U> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public <U extends Number & Comparable<?>> NumberPredicateTester<T, U, NEXT>
    where(NumberAttribute<T, U> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public <U extends Comparable<?>> ComparablePredicateTester<T, U, NEXT>
    where(ComparableAttribute<T, U> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public StringPredicateTester<T, NEXT> where(StringAttribute<T> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public <U extends Entity> PathBuilder<T, U, NEXT> whereNot(EntityAttribute<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public <U> PredicateTester<T, U, NEXT> whereNot(Attribute<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public <U extends Number & Comparable<?>> NumberPredicateTester<T, U, NEXT> whereNot(NumberAttribute<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public <U extends Comparable<?>> ComparablePredicateTester<T, U, NEXT> whereNot(ComparableAttribute<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public StringPredicateTester<T, NEXT> whereNot(StringAttribute<T> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public NEXT where(Predicate<T> predicate) {
        return getBuilder().and(predicate);
    }
}
