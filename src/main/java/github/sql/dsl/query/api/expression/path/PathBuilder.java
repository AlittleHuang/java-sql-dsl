package github.sql.dsl.query.api.expression.path;

import github.sql.dsl.query.api.builder.combination.ComparablePredicateBuilder;
import github.sql.dsl.query.api.builder.combination.NumberPredicateBuilder;
import github.sql.dsl.query.api.builder.combination.PredicateBuilder;
import github.sql.dsl.query.api.builder.combination.StringPredicateBuilder;
import github.sql.dsl.query.api.expression.path.attribute.*;

import java.util.Date;

public interface PathBuilder<T, U, NEXT> {

    <R extends Entity> PathBuilder<T, R, NEXT> map(EntityAttribute<U, R> column);

    <R extends Number> NumberPredicateBuilder<T, R, NEXT> map(NumberAttribute<U, R> column);

    <R extends Date> ComparablePredicateBuilder<T, R, NEXT> map(ComparableAttribute<U, R> column);

    <R extends Date> PredicateBuilder<T, R, NEXT> map(Attribute<U, R> attribute);

    StringPredicateBuilder<T, NEXT> map(StringAttribute<U> column);


}
