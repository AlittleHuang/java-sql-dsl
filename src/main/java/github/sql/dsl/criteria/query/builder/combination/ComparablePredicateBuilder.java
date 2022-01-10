package github.sql.dsl.criteria.query.builder.combination;

public interface ComparablePredicateBuilder<T, U extends Comparable<?>, NEXT> extends PredicateBuilder<T, U, NEXT> {

    NEXT ge(U value);

    NEXT gt(U value);

    NEXT le(U value);

    NEXT between(U a, U b);

    NEXT lt(U value);

    @Override
    ComparablePredicateBuilder<T, U, NEXT> nullIf(U value);

}
