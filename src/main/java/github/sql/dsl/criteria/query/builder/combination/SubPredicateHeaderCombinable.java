package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.expression.path.Entity;
import github.sql.dsl.criteria.query.expression.path.PathBuilder;
import github.sql.dsl.criteria.query.expression.path.attribute.*;

import java.util.Date;

public interface SubPredicateHeaderCombinable<T, NEXT> {

    <U extends Entity> PathBuilder<T, U, NEXT> get(EntityAttribute<T, U> column);

    <U> PredicateBuilder<T, U, NEXT> get(Attribute<T, U> attribute);

    <U extends Number> NumberPredicateBuilder<T, U, NEXT> get(NumberAttribute<T, U> column);

    <U extends Date> ComparablePredicateBuilder<T, U, NEXT> get(ComparableAttribute<T, U> column);

    StringPredicateBuilder<T, NEXT> get(StringAttribute<T> column);

    <U extends Entity> PathBuilder<T, U, NEXT> not(EntityAttribute<T, U> column);

    <U> PredicateBuilder<T, U, NEXT> not(Attribute<T, U> attribute);

    <U extends Number> NumberPredicateBuilder<T, U, NEXT> not(NumberAttribute<T, U> column);

    <U extends Date> ComparablePredicateBuilder<T, U, NEXT> not(ComparableAttribute<T, U> column);

    StringPredicateBuilder<T, NEXT> not(StringAttribute<T> column);

}
