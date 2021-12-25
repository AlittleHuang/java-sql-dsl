package github.sql.dsl.query.api.condition;

import github.sql.dsl.query.api.WhereClauses;

import java.util.Arrays;
import java.util.Collection;

public interface ComparableConditionBuilder<T, U extends Comparable<?>> {

    WhereClauses<T> eq(U value);

    WhereClauses<T> ge(U value);

    WhereClauses<T> gt(U value);

    WhereClauses<T> le(U value);

    WhereClauses<T> between(U a, U b);

    WhereClauses<T> lt(U value);

    WhereClauses<T> isNull();

    @SuppressWarnings("unchecked")
    default WhereClauses<T> in(U... values) {
        return in(Arrays.asList(values));
    }

    WhereClauses<T> in(Collection<U> values);

}
