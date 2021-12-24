package github.sql.dsl.query.api.column;

import java.util.Date;
import java.util.function.Function;

@FunctionalInterface
public interface DateColumn<T, U extends Date> extends Function<T, U> {

}
