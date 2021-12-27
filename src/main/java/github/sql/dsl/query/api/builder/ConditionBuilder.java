package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.WhereClauses;

import java.util.Arrays;
import java.util.Collection;

public interface ConditionBuilder<T,U> {

    WhereClauses<T> isNull();

    WhereClauses<T> eq(U value);

    @SuppressWarnings("unchecked")
    default WhereClauses<T> in(U... values) {
        return in(Arrays.asList(values));
    }

    WhereClauses<T> in(Collection<U> values);

    ConditionBuilder<T, U> nullIf(U value);


}
