package github.sql.dsl.criteria.query.builder.combination;

public interface ComparablePredicateTester<T, U extends Comparable<?>, NEXT> extends PredicateTester<T, U, NEXT> {

    NEXT ge(U value);

    NEXT gt(U value);

    NEXT le(U value);

    NEXT between(U a, U b);

    NEXT lt(U value);

    @Override
    ComparablePredicateTester<T, U, NEXT> nullIf(U value);

}
