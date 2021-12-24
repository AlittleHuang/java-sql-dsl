package github.sql.dsl.query.api.column;

import java.util.function.Function;

@FunctionalInterface
public interface BytesColumn<T> extends Function<T, byte[]> {


}
