package github.sql.dsl.query.suport;

import github.sql.dsl.util.Array;
import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.PathExpression;
import github.sql.dsl.query.suport.builder.component.Order;

public interface CriteriaQuery {

    Expression<Boolean> getRestriction();

    Array<Order> getOrderList();

    Array<Expression<?>> getGroupList();

    Array<Expression<?>> getSelection();

    Array<PathExpression<?>> getFetch();

}
