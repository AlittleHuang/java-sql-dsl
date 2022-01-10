package github.sql.dsl.query.jdbc.sql;

import java.util.List;

public interface PreparedSql {

    String getSql();

    List<Object> getArgs();

}
