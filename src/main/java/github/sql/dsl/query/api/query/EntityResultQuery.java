package github.sql.dsl.query.api.query;

import github.sql.dsl.util.Assert;

import java.util.List;
import java.util.Objects;

public interface EntityResultQuery<T> {
    int NON = Integer.MIN_VALUE / 2;

    int count();

    default T getFirst() {
        return getFirst(NON);
    }

    default T getFirst(int offset) {
        List<T> list = getResultList(offset, 1);
        return list.isEmpty() ? null : list.get(0);
    }

    default T getOne() {
        return Objects.requireNonNull(getSingleResult(NON));
    }

    default T getSingleResult() {
        return getSingleResult(NON);
    }

    default T getSingleResult(int offset) {
        List<T> list = getResultList(offset, 2);
        Assert.state(list.size() <= 1, "found more than one");
        return list.isEmpty() ? null : list.get(0);
    }

    List<T> getResultList(int offset, int maxResul);

    default List<T> getResultList(int offset) {
        return getResultList(offset, NON);
    }

    default List<T> getResultList() {
        return getResultList(NON, NON);
    }

    default boolean exist() {
        return exist(NON);
    }

    boolean exist(int offset);

}
