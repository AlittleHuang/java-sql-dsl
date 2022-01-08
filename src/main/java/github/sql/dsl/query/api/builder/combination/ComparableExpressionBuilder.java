package github.sql.dsl.query.api.builder.combination;

public interface ComparableExpressionBuilder<T, U extends Comparable<?>, NEXT> extends ExpressionBuilder<T, U, NEXT> {

    NEXT ge(U value);

    NEXT gt(U value);

    NEXT le(U value);

    NEXT between(U a, U b);

    NEXT lt(U value);

    @Override
    ComparableExpressionBuilder<T, U, NEXT> nullIf(U value);

}
