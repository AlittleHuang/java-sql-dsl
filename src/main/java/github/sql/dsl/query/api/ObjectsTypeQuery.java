package github.sql.dsl.query.api;

import github.sql.dsl.util.Assert;

import java.util.List;

public interface ObjectsTypeQuery {

    default Object[] getFirstObjects() {
        return getFirstObjects(TypeQuery.NON);
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
        return getSingleObjects(TypeQuery.NON);
    }

    List<Object[]> getObjectsList(int offset, int maxResult);

    default List<Object[]> getObjectsList(int offset) {
        return getObjectsList(offset, TypeQuery.NON);
    }

    default List<Object[]> getObjectsList() {
        return getObjectsList(TypeQuery.NON, TypeQuery.NON);
    }

}
