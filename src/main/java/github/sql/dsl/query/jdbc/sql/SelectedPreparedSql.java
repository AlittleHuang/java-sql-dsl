package github.sql.dsl.query.jdbc.sql;

import github.sql.dsl.query.api.expression.PathExpression;

import java.util.List;

public interface SelectedPreparedSql extends PreparedSql {

    List<PathExpression<?>> getSelectedPath();

}
