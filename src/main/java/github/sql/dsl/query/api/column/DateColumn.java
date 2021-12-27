package github.sql.dsl.query.api.column;

import java.util.Date;

@FunctionalInterface
public interface DateColumn<T, U extends Date> extends Column<T, U> {

}
