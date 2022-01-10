package github.sql.dsl.internal.jdbc.sql;

import java.util.List;

public interface PreparedSql {

    String getSql();

    List<Object> getArgs();

}
