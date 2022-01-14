package github.sql.dsl.criteria.query.support.builder.query;

import github.sql.dsl.criteria.query.builder.combination.*;
import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.path.Entity;
import github.sql.dsl.criteria.query.expression.path.PathBuilder;
import github.sql.dsl.criteria.query.expression.path.attribute.*;
import github.sql.dsl.criteria.query.support.builder.criteria.PredicateAssemblerImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.function.Function;

public class SubPredicateHeaderCombinableImpl<T, NEXT> implements SubPredicateHeaderCombinable<T, NEXT> {

    private final Function<Expression<Boolean>, NEXT> mapper;

    public SubPredicateHeaderCombinableImpl(Function<Expression<Boolean>, NEXT> mapper) {
        this.mapper = mapper;
    }

    @NotNull
    private PredicateAssemblerImpl<T, NEXT> getBuilder() {
        return new PredicateAssemblerImpl<>(null, mapper);
    }


    @Override
    public <U extends Entity> PathBuilder<T, U, NEXT> get(EntityAttribute<T, U> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public <U> PredicateTester<T, U, NEXT> get(Attribute<T, U> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public <U extends Number> NumberPredicateTester<T, U, NEXT>
    get(NumberAttribute<T, U> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public <U extends Date> ComparablePredicateTester<T, U, NEXT>
    get(ComparableAttribute<T, U> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public StringPredicateTester<T, NEXT> get(StringAttribute<T> attribute) {
        return getBuilder().and(attribute);
    }

    @Override
    public <U extends Entity> PathBuilder<T, U, NEXT> not(EntityAttribute<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public <U> PredicateTester<T, U, NEXT> not(Attribute<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public <U extends Number> NumberPredicateTester<T, U, NEXT> not(NumberAttribute<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public <U extends Date> ComparablePredicateTester<T, U, NEXT> not(ComparableAttribute<T, U> attribute) {
        return getBuilder().andNot(attribute);
    }

    @Override
    public StringPredicateTester<T, NEXT> not(StringAttribute<T> attribute) {
        return getBuilder().andNot(attribute);
    }


}
