package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.builder.PredicateAssembler;
import github.sql.dsl.criteria.query.expression.Predicate;
import github.sql.dsl.criteria.query.expression.path.Entity;
import github.sql.dsl.criteria.query.expression.path.PathBuilder;
import github.sql.dsl.criteria.query.expression.path.attribute.*;

import java.util.Date;

public interface Whereable<T, NEXT> {

    <U extends Entity> PathBuilder<T, U, NEXT> where(EntityAttribute<T, U> column);

    <U> PredicateTester<T, U, NEXT> where(Attribute<T, U> attribute);

    <U extends Number> NumberPredicateTester<T, U, NEXT> where(NumberAttribute<T, U> column);

    <U extends Date> ComparablePredicateTester<T, U, NEXT> where(ComparableAttribute<T, U> column);

    StringPredicateTester<T, NEXT> where(StringAttribute<T> column);

    <U extends Entity> PathBuilder<T, U, NEXT> whereNot(EntityAttribute<T, U> column);

    <U> PredicateTester<T, U, NEXT> whereNot(Attribute<T, U> attribute);

    <U extends Number> NumberPredicateTester<T, U, NEXT> whereNot(NumberAttribute<T, U> column);

    <U extends Date> ComparablePredicateTester<T, U, NEXT> whereNot(ComparableAttribute<T, U> column);

    StringPredicateTester<T, NEXT> whereNot(StringAttribute<T> column);

    NEXT Where(PredicateAssembler.Builder<T, NEXT> builder);

    NEXT where(Predicate<T> predicate);

}
