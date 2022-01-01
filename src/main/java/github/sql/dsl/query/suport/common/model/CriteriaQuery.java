package github.sql.dsl.query.suport.common.model;

import github.sql.dsl.query.api.Expression;
import github.sql.dsl.query.api.PathExpression;

import java.util.List;

public interface CriteriaQuery {

    Expression<Boolean> getRestriction();

    List<Order> getOrderList();

    List<Expression<?>> getGroupList();

    List<Expression<?>> getSelection();

    List<PathExpression<?>> getFetch();

}
