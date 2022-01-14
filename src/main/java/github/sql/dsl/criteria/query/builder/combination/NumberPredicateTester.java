package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.path.attribute.NumberAttribute;

public interface NumberPredicateTester<T, U extends Number, NEXT> extends PredicateTester<T, U, NEXT> {

    NumberPredicateTester<T, U, NEXT> add(U v);

    NumberPredicateTester<T, U, NEXT> subtract(U v);

    NumberPredicateTester<T, U, NEXT> multiply(U v);

    NumberPredicateTester<T, U, NEXT> divide(U v);

    NumberPredicateTester<T, U, NEXT> mod(U v);

    @Override
    NumberPredicateTester<T, U, NEXT> nullIf(U value);

    NEXT ge(U value);

    NEXT gt(U value);

    NEXT le(U value);

    NEXT between(U a, U b);

    NEXT lt(U value);


    NumberPredicateTester<T, U, NEXT> add(NumberAttribute<T, U> v);

    NumberPredicateTester<T, U, NEXT> subtract(NumberAttribute<T, U> v);

    NumberPredicateTester<T, U, NEXT> multiply(NumberAttribute<T, U> v);

    NumberPredicateTester<T, U, NEXT> divide(NumberAttribute<T, U> v);

    NumberPredicateTester<T, U, NEXT> mod(NumberAttribute<T, U> v);

    NEXT ge(NumberAttribute<T, U> value);

    NEXT gt(NumberAttribute<T, U> value);

    NEXT le(NumberAttribute<T, U> value);

    NEXT between(NumberAttribute<T, U> a, NumberAttribute<T, U> b);

    NEXT lt(NumberAttribute<T, U> value);

    NumberPredicateTester<T, U, NEXT> add(Expression<U> v);

    NumberPredicateTester<T, U, NEXT> subtract(Expression<U> v);

    NumberPredicateTester<T, U, NEXT> multiply(Expression<U> v);

    NumberPredicateTester<T, U, NEXT> divide(Expression<U> v);

    NumberPredicateTester<T, U, NEXT> mod(Expression<U> v);

    NEXT ge(Expression<U> value);

    NEXT gt(Expression<U> value);

    NEXT le(Expression<U> value);

    NEXT between(Expression<U> a, Expression<U> b);

    NEXT lt(Expression<U> value);


}
