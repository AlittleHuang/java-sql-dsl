package github.sql.dsl.query.api.column;

import java.util.function.Function;

@FunctionalInterface
public interface StringColumn<T> extends Function<T, String> {



}
