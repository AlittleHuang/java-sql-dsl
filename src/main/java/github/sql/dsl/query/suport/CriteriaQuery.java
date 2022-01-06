package github.sql.dsl.query.suport;

import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.PathExpression;
import github.sql.dsl.query.suport.builder.component.Order;
import github.sql.dsl.query.suport.builder.component.Selection;
import github.sql.dsl.util.Array;

public interface CriteriaQuery {

    Expression<Boolean> getRestriction();

    Array<Order> getOrderList();

    Array<Expression<?>> getGroupList();

    Array<Selection<?>> getSelectionList();

    Array<PathExpression<?>> getFetchList();

}
