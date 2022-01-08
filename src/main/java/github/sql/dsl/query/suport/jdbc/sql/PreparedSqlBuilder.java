package github.sql.dsl.query.suport.jdbc.sql;

public interface PreparedSqlBuilder {

    SelectedPreparedSql getEntityList(int offset, int maxResultant);

    PreparedSql getObjectsList(int offset, int maxResultant);

    PreparedSql exist(int offset);

    PreparedSql count();

    SelectedPreparedSql getProjectionList(int offset, int maxResult, Class<?> projectionType);
}
