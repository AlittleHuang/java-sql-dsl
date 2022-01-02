package github.sql.dsl.query.api.expression;

public interface ComparableExpressionBuilder<T, U extends Comparable<?>, V> extends ExpressionBuilder<T, U, V> {

    V ge(U value);

    V gt(U value);

    V le(U value);

    V between(U a, U b);

    V lt(U value);

    @Override
    ComparableExpressionBuilder<T, U, V> nullIf(U value);

}
