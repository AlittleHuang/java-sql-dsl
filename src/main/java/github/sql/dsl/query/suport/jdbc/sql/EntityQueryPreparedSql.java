package github.sql.dsl.query.suport.jdbc.sql;

import github.sql.dsl.query.api.expression.PathExpression;

import java.util.List;

public interface EntityQueryPreparedSql extends PreparedSql {

    List<PathExpression<?>> getSelectedPath();

}
