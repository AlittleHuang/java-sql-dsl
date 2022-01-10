package github.sql.dsl.criteria.query.expression.path;

import github.sql.dsl.criteria.query.builder.combination.ComparablePredicateBuilder;
import github.sql.dsl.criteria.query.builder.combination.NumberPredicateBuilder;
import github.sql.dsl.criteria.query.builder.combination.PredicateBuilder;
import github.sql.dsl.criteria.query.builder.combination.StringPredicateBuilder;
import github.sql.dsl.criteria.query.expression.path.attribute.*;

import java.util.Date;

public interface PathBuilder<T, U, NEXT> {

    <R extends Entity> PathBuilder<T, R, NEXT> map(EntityAttribute<U, R> column);

    <R extends Number> NumberPredicateBuilder<T, R, NEXT> map(NumberAttribute<U, R> column);

    <R extends Date> ComparablePredicateBuilder<T, R, NEXT> map(ComparableAttribute<U, R> column);

    <R extends Date> PredicateBuilder<T, R, NEXT> map(Attribute<U, R> attribute);

    StringPredicateBuilder<T, NEXT> map(StringAttribute<U> column);


}
