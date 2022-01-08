package github.sql.dsl.query.suport.jdbc.sql;

import java.util.List;

public interface PreparedSqlExecutor {

    <T> List<T> getEntityList(SelectedPreparedSql sql, Class<T> entityType);

    <T, R> List<R> getProjectionList(SelectedPreparedSql sql, Class<T> entityType, Class<R> projectionType);

    List<Object[]> listResult(PreparedSql sql, Class<?> entityType);

    boolean exist(PreparedSql sql, Class<?> entityType);

    int count(PreparedSql sql, Class<?> entityType);

}
