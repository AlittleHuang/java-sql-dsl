package github.sql.dsl.internal.jdbc.sql;

import github.sql.dsl.criteria.query.support.CriteriaQuery;

public interface SqlBuilderFactory {

    PreparedSqlBuilder get(CriteriaQuery criteria, Class<?> type);

}
