package github.sql.dsl.criteria.query.support.builder.query;

import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.PathExpression;
import github.sql.dsl.criteria.query.support.CriteriaQuery;
import github.sql.dsl.criteria.query.support.builder.component.Order;
import github.sql.dsl.util.Array;

public class CriteriaQueryImpl implements CriteriaQuery {

    public static final CriteriaQueryImpl EMPTY = new CriteriaQueryImpl(null, null, null, null, null);


    private final Expression<Boolean> restriction;
    private final Array<Order> orderList;
    private final Array<Expression<?>> groupList;
    private final Array<Expression<?>> selection;
    private final Array<PathExpression<?>> fetch;

    public CriteriaQueryImpl(Expression<Boolean> restriction,
                             Array<Order> orderList,
                             Array<Expression<?>> groupList,
                             Array<Expression<?>> selection,
                             Array<PathExpression<?>> fetch) {
        this.restriction = restriction;
        this.orderList = orderList;
        this.groupList = groupList;
        this.selection = selection;
        this.fetch = fetch;
    }

    public static CriteriaQueryImpl from(CriteriaQuery criteriaQuery) {
        if (criteriaQuery instanceof CriteriaQueryImpl) {
            return (CriteriaQueryImpl) criteriaQuery;
        } else if (criteriaQuery == null) {
            return EMPTY;
        }
        return new CriteriaQueryImpl(
                criteriaQuery.getRestriction(),
                criteriaQuery.getOrderList(),
                criteriaQuery.getGroupList(),
                criteriaQuery.getSelectionList(),
                criteriaQuery.getFetchList()
        );
    }

    public CriteriaQueryImpl updateRestriction(Expression<Boolean> restriction) {
        return new CriteriaQueryImpl(restriction, orderList, groupList, selection, fetch);
    }

    public CriteriaQueryImpl updateOrderList(Array<Order> orderList) {
        return new CriteriaQueryImpl(restriction, orderList, groupList, selection, fetch);
    }

    public CriteriaQueryImpl updateGroupList(Array<Expression<?>> groupList) {
        return new CriteriaQueryImpl(restriction, orderList, groupList, selection, fetch);
    }

    public CriteriaQueryImpl updateSelection(Array<Expression<?>> selection) {
        return new CriteriaQueryImpl(restriction, orderList, groupList, selection, fetch);
    }

    public CriteriaQueryImpl updateFetch(Array<PathExpression<?>> fetch) {
        return new CriteriaQueryImpl(restriction, orderList, groupList, selection, fetch);
    }

    @Override
    public Expression<Boolean> getRestriction() {
        return restriction;
    }

    @Override
    public Array<Order> getOrderList() {
        return orderList;
    }

    @Override
    public Array<Expression<?>> getGroupList() {
        return groupList;
    }

    @Override
    public Array<Expression<?>> getSelectionList() {
        return selection;
    }

    @Override
    public Array<PathExpression<?>> getFetchList() {
        return fetch;
    }


}
