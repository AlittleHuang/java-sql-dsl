package github.sql.dsl.query.api.column;

import java.util.function.Function;

@FunctionalInterface
public interface NumberColumn<T, R extends Number> extends Function<T, R> {



}
