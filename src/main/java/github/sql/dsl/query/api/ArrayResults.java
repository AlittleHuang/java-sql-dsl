package github.sql.dsl.query.api;

import java.util.List;

public interface ArrayResults {

    Object[] single(int offset);

    default Object[] single() {
        return single(0);
    }

    List<Object[]> list(int offset, int maxResultant);

    default List<Object[]> list(int offset) {
        return list(offset, -1);
    }

    default List<Object[]> list() {
        return list(0, -1);
    }

}
