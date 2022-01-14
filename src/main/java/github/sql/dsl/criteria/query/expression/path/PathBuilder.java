package github.sql.dsl.criteria.query.expression.path;

import github.sql.dsl.criteria.query.builder.combination.ComparablePredicateTester;
import github.sql.dsl.criteria.query.builder.combination.NumberPredicateTester;
import github.sql.dsl.criteria.query.builder.combination.PredicateTester;
import github.sql.dsl.criteria.query.builder.combination.StringPredicateTester;
import github.sql.dsl.criteria.query.expression.path.attribute.*;

import java.util.Date;

public interface PathBuilder<T, U, NEXT> {

    <R extends Entity> PathBuilder<T, R, NEXT> map(EntityAttribute<U, R> column);

    <R extends Number> NumberPredicateTester<T, R, NEXT> map(NumberAttribute<U, R> column);

    <R extends Date> ComparablePredicateTester<T, R, NEXT> map(ComparableAttribute<U, R> column);

    <R extends Date> PredicateTester<T, R, NEXT> map(Attribute<U, R> attribute);

    StringPredicateTester<T, NEXT> map(StringAttribute<U> column);


}
