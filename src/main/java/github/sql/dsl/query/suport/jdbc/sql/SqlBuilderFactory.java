package github.sql.dsl.query.suport.jdbc.sql;

import github.sql.dsl.query.suport.CriteriaQuery;

public interface SqlBuilderFactory {

    PreparedSqlBuilder get(CriteriaQuery criteria, Class<?> type);

}
