package github.sql.dsl.query.api.column;

import java.util.function.Function;

@FunctionalInterface
public interface BooleanColumn<T> extends Function<T, Boolean> {



}
