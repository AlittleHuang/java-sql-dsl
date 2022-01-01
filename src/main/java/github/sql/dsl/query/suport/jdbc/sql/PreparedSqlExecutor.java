package github.sql.dsl.query.suport.jdbc.sql;

import java.util.List;

public interface PreparedSqlExecutor {

    <T> List<T> getEntityList(EntityQueryPreparedSql sql, Class<T> entityType);

    List<Object[]> listResult(PreparedSql sql, Class<?> entityType);

    boolean exist(PreparedSql sql, Class<?> entityType);

    int count(PreparedSql sql, Class<?> entityType);

}
