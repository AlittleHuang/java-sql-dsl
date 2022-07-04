package github.sql.dsl.criteria.query.support;

import github.sql.dsl.criteria.query.expression.SqlExpression;
import github.sql.dsl.criteria.query.expression.PathExpression;
import github.sql.dsl.criteria.query.support.builder.component.Order;
import github.sql.dsl.util.Array;

public interface SqlCriteriaQuery {

    SqlExpression<Boolean> getRestriction();

    Array<Order> getOrderList();

    Array<SqlExpression<?>> getGroupList();

    Array<SqlExpression<?>> getSelectionList();

    Array<PathExpression<?>> getFetchList();

}
