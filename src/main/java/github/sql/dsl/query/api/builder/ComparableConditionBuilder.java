package github.sql.dsl.query.api.builder;

public interface ComparableConditionBuilder<T, U extends Comparable<?>, V> extends ConditionBuilder<T, U, V> {

    V ge(U value);

    V gt(U value);

    V le(U value);

    V between(U a, U b);

    V lt(U value);

    @Override
    ComparableConditionBuilder<T, U, V> nullIf(U value);

}
