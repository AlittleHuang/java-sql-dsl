package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.expression.Expression;
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
    NEXT ge(Expression<U> value);

    /**
     * greater than (>)
     */
    NEXT gt(Expression<U> value);

    /**
     * less than or equal to (<=)
     */
    NEXT le(Expression<U> value);

    NEXT between(Expression<U> a, Expression<U> b);

    /**
     * less than (<)
     */
    NEXT lt(Expression<U> value);

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
