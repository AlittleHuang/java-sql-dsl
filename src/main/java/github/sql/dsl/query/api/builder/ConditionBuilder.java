package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.WhereClauses;

import java.util.Arrays;
import java.util.Collection;

public interface ConditionBuilder<T, U, V> {

    V isNull();

    V eq(U value);

    @SuppressWarnings("unchecked")
    default V in(U... values) {
        return in(Arrays.asList(values));
    }

    V in(Collection<U> values);

    ConditionBuilder<T, U, V> nullIf(U value);


}
