package github.sql.dsl.query.suport.jdbc.sql;

import java.util.List;

public interface PreparedSql {

    String getSql();

    List<Object> getArgs();

}
