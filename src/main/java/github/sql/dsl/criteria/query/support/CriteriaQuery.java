package github.sql.dsl.criteria.query.support;

import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.PathExpression;
import github.sql.dsl.criteria.query.support.builder.component.Order;
import github.sql.dsl.criteria.query.support.builder.component.Selection;
import github.sql.dsl.util.Array;

public interface CriteriaQuery {

    Expression<Boolean> getRestriction();

    Array<Order> getOrderList();

    Array<Expression<?>> getGroupList();

    Array<Selection<?>> getSelectionList();

    Array<PathExpression<?>> getFetchList();

}
