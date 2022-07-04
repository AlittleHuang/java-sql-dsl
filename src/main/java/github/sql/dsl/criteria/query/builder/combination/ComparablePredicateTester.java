package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.expression.SqlExpression;
import github.sql.dsl.criteria.query.expression.path.attribute.ComparableAttribute;

public interface ComparablePredicateTester<T, U extends Comparable<?>, NEXT> extends PredicateTester<T, U, NEXT> {

    /**
     * greater than or equal to (>=)
     */
    NEXT ge(U value);

    /**
     * greater than (>)
     */
    NEXT gt(U value);

    /**
     * less than or equal to (<=)
     */
    NEXT le(U value);

    /**
     * less than (<)
     */
    NEXT lt(U value);

    NEXT between(U a, U b);


    /**
     * greater than or equal to (>=)
     */
    NEXT ge(SqlExpression<U> value);

    /**
     * greater than (>)
     */
    NEXT gt(SqlExpression<U> value);

    /**
     * less than or equal to (<=)
     */
    NEXT le(SqlExpression<U> value);

    NEXT between(SqlExpression<U> a, SqlExpression<U> b);

    /**
     * less than (<)
     */
    NEXT lt(SqlExpression<U> value);

    /**
     * greater than or equal to (>=)
     */
    NEXT ge(ComparableAttribute<T, U> value);

    /**
     * greater than (>)
     */
    NEXT gt(ComparableAttribute<T, U> value);

    /**
     * less than or equal to (<=)
     */
    NEXT le(ComparableAttribute<T, U> value);

    NEXT between(ComparableAttribute<T, U> a, ComparableAttribute<T, U> b);

    /**
     * less than (<)
     */
    NEXT lt(ComparableAttribute<T, U> value);

}
