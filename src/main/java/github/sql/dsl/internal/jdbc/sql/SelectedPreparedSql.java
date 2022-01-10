package github.sql.dsl.internal.jdbc.sql;

import github.sql.dsl.criteria.query.expression.PathExpression;

import java.util.List;

public interface SelectedPreparedSql extends PreparedSql {

    List<PathExpression<?>> getSelectedPath();

}
