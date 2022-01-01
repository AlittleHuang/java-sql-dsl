package github.sql.dsl.query.suport.jdbc.sql;

public interface PreparedSqlBuilder {

    EntityQueryPreparedSql getEntityList(int offset, int maxResultant);

    PreparedSql getObjectsList(int offset, int maxResultant);

    PreparedSql exist(int offset);

    PreparedSql count();

}
