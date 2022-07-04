package github.sql.dsl.internal.jdbc.sql;

import github.sql.dsl.criteria.query.support.SqlCriteriaQuery;

public interface SqlBuilderFactory {

    PreparedSqlBuilder get(SqlCriteriaQuery criteria, Class<?> type);

}
