package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.WhereClauses;

public interface ComparableConditionBuilder<T, U extends Comparable<?>> extends ConditionBuilder<T, U> {

    WhereClauses<T> ge(U value);

    WhereClauses<T> gt(U value);

    WhereClauses<T> le(U value);

    WhereClauses<T> between(U a, U b);

    WhereClauses<T> lt(U value);

    @Override
    ComparableConditionBuilder<T, U> nullIf(U value);

}
