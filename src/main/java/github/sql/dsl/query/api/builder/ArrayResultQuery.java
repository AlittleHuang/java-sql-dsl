package github.sql.dsl.query.api.builder;

import github.sql.dsl.util.Assert;

import java.util.List;

public interface ArrayResultQuery {

    default Object[] getFirstObjects() {
        return getFirstObjects(EntityResultQuery.NON);
    }

    default Object[] getFirstObjects(int offset) {
        List<Object[]> list = getObjectsList(offset, 1);
        return list.isEmpty() ? null : list.get(0);
    }

    default Object[] getSingleObjects(int offset) {
        List<Object[]> list = getObjectsList(offset, 2);
        Assert.state(list.size() <= 1, "found more than one");
        return list.isEmpty() ? null : list.get(0);
    }

    default Object[] getSingleObjects() {
        return getSingleObjects(EntityResultQuery.NON);
    }

    List<Object[]> getObjectsList(int offset, int maxResult);

    default List<Object[]> getObjectsList(int offset) {
        return getObjectsList(offset, EntityResultQuery.NON);
    }

    default List<Object[]> getObjectsList() {
        return getObjectsList(EntityResultQuery.NON, EntityResultQuery.NON);
    }

}
