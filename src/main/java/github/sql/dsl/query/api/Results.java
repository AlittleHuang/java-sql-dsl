package github.sql.dsl.query.api;

import java.util.List;

public interface Results<T> {

    default int count() {
        return count(0);
    }

    int count(int offset);

    default T single() {
        return single(0);
    }

    T single(int offset);

    List<T> list(int offset, int maxResultant);

    default List<T> list(int offset) {
        return list(offset, -1);
    }

    default List<T> list() {
        return list(0, -1);
    }

    default boolean exist() {
        return exist(0);
    }

    boolean exist(int offset);

    <U> Results<T> projection(Class<U> projectionType);

}
