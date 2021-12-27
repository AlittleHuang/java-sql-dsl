package github.sql.dsl.query.api;

import java.util.List;

public interface ArrayResults {

    int NON_MAX_RESULT = -1;

    Object[] getSingleResult(int offset);

    default Object[] getSingleResult() {
        return getSingleResult(0);
    }

    List<Object[]> getResultList(int offset, int maxResult);

    default List<Object[]> getResultList(int offset) {
        return getResultList(offset, NON_MAX_RESULT);
    }

    default List<Object[]> getResultList() {
        return getResultList(0, NON_MAX_RESULT);
    }

}
