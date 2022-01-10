package github.sql.dsl.query.jdbc.sql;

import github.sql.dsl.query.api.suport.CriteriaQuery;

public interface SqlBuilderFactory {

    PreparedSqlBuilder get(CriteriaQuery criteria, Class<?> type);

}
