package github.sql.dsl.criteria.query.builder.combination;

import java.util.Arrays;
import java.util.Collection;

public interface PredicateTester<T, U, NEXT> {

    NEXT isNull();

    NEXT eq(U value);

    NEXT diff(U value);

    @SuppressWarnings("unchecked")
    default NEXT in(U... values) {
        return in(Arrays.asList(values));
    }

    NEXT in(Collection<U> values);

    PredicateTester<T, U, NEXT> nullIf(U value);


}
