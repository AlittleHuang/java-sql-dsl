package github.sql.dsl.query.api;

import java.util.Arrays;
import java.util.Collection;

public interface ExpressionBuilder<T, U, V> {

    V isNull();

    V eq(U value);

    V diff(U value);

    @SuppressWarnings("unchecked")
    default V in(U... values) {
        return in(Arrays.asList(values));
    }

    V in(Collection<U> values);

    ExpressionBuilder<T, U, V> nullIf(U value);


}
