package github.sql.dsl.query.api.builder.combination;

import java.util.Arrays;
import java.util.Collection;

public interface PredicateBuilder<T, U, NEXT> {

    NEXT isNull();

    NEXT eq(U value);

    NEXT diff(U value);

    @SuppressWarnings("unchecked")
    default NEXT in(U... values) {
        return in(Arrays.asList(values));
    }

    NEXT in(Collection<U> values);

    PredicateBuilder<T, U, NEXT> nullIf(U value);


}