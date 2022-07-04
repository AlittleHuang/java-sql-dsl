package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.expression.SqlExpression;
import github.sql.dsl.criteria.query.expression.path.attribute.NumberAttribute;

public interface NumberPredicateTester<T, U extends Number & Comparable<?>, NEXT> extends ComparablePredicateTester<T, U, NEXT> {

    NumberPredicateTester<T, U, NEXT> add(U v);

    NumberPredicateTester<T, U, NEXT> subtract(U v);

    NumberPredicateTester<T, U, NEXT> multiply(U v);

    NumberPredicateTester<T, U, NEXT> divide(U v);

    NumberPredicateTester<T, U, NEXT> mod(U v);


    NumberPredicateTester<T, U, NEXT> add(SqlExpression<U> v);

    NumberPredicateTester<T, U, NEXT> subtract(SqlExpression<U> v);

    NumberPredicateTester<T, U, NEXT> multiply(SqlExpression<U> v);

    NumberPredicateTester<T, U, NEXT> divide(SqlExpression<U> v);

    NumberPredicateTester<T, U, NEXT> mod(SqlExpression<U> v);


    NumberPredicateTester<T, U, NEXT> add(NumberAttribute<T, U> v);

    NumberPredicateTester<T, U, NEXT> subtract(NumberAttribute<T, U> v);

    NumberPredicateTester<T, U, NEXT> multiply(NumberAttribute<T, U> v);

    NumberPredicateTester<T, U, NEXT> divide(NumberAttribute<T, U> v);

    NumberPredicateTester<T, U, NEXT> mod(NumberAttribute<T, U> v);


}
