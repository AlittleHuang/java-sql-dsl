package github.sql.dsl.criteria.query.support.builder.query;

import github.sql.dsl.criteria.query.expression.SqlExpression;
import github.sql.dsl.criteria.query.expression.PathExpression;
import github.sql.dsl.criteria.query.support.SqlCriteriaQuery;
import github.sql.dsl.criteria.query.support.builder.component.Order;
import github.sql.dsl.util.Array;

public class SqlCriteriaQueryImpl implements SqlCriteriaQuery {

    public static final SqlCriteriaQueryImpl EMPTY = new SqlCriteriaQueryImpl(null, null, null, null, null);


    private final SqlExpression<Boolean> restriction;
    private final Array<Order> orderList;
    private final Array<SqlExpression<?>> groupList;
    private final Array<SqlExpression<?>> selection;
    private final Array<PathExpression<?>> fetch;

    public SqlCriteriaQueryImpl(SqlExpression<Boolean> restriction,
                                Array<Order> orderList,
                                Array<SqlExpression<?>> groupList,
                                Array<SqlExpression<?>> selection,
                                Array<PathExpression<?>> fetch) {
        this.restriction = restriction;
        this.orderList = orderList;
        this.groupList = groupList;
        this.selection = selection;
        this.fetch = fetch;
    }

    public static SqlCriteriaQueryImpl from(SqlCriteriaQuery criteriaQuery) {
        if (criteriaQuery instanceof SqlCriteriaQueryImpl) {
            return (SqlCriteriaQueryImpl) criteriaQuery;
        } else if (criteriaQuery == null) {
            return EMPTY;
        }
        return new SqlCriteriaQueryImpl(
                criteriaQuery.getRestriction(),
                criteriaQuery.getOrderList(),
                criteriaQuery.getGroupList(),
                criteriaQuery.getSelectionList(),
                criteriaQuery.getFetchList()
        );
    }

    public SqlCriteriaQueryImpl updateRestriction(SqlExpression<Boolean> restriction) {
        return new SqlCriteriaQueryImpl(restriction, orderList, groupList, selection, fetch);
    }

    public SqlCriteriaQueryImpl updateOrderList(Array<Order> orderList) {
        return new SqlCriteriaQueryImpl(restriction, orderList, groupList, selection, fetch);
    }

    public SqlCriteriaQueryImpl updateGroupList(Array<SqlExpression<?>> groupList) {
        return new SqlCriteriaQueryImpl(restriction, orderList, groupList, selection, fetch);
    }

    public SqlCriteriaQueryImpl updateSelection(Array<SqlExpression<?>> selection) {
        return new SqlCriteriaQueryImpl(restriction, orderList, groupList, selection, fetch);
    }

    public SqlCriteriaQueryImpl updateFetch(Array<PathExpression<?>> fetch) {
        return new SqlCriteriaQueryImpl(restriction, orderList, groupList, selection, fetch);
    }

    @Override
    public SqlExpression<Boolean> getRestriction() {
        return restriction;
    }

    @Override
    public Array<Order> getOrderList() {
        return orderList;
    }

    @Override
    public Array<SqlExpression<?>> getGroupList() {
        return groupList;
    }

    @Override
    public Array<SqlExpression<?>> getSelectionList() {
        return selection;
    }

    @Override
    public Array<PathExpression<?>> getFetchList() {
        return fetch;
    }


}
