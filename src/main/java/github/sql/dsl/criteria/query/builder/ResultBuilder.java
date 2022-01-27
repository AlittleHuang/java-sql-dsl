package github.sql.dsl.criteria.query.builder;

import github.sql.dsl.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface ResultBuilder<T> {

    int IGNORED = Integer.MIN_VALUE / 2;

    int count();

    List<T> getList(int offset, int maxResult);

    default Optional<T> first() {
        return Optional.ofNullable(getFirst());
    }

    default Optional<T> first(int offset) {
        return Optional.ofNullable(getFirst(offset));
    }

    default T getFirst() {
        return getFirst(IGNORED);
    }

    default T getFirst(int offset) {
        List<T> list = getList(offset, 1);
        return list.isEmpty() ? null : list.get(0);
    }

    default T requireSingle() {
        return Objects.requireNonNull(getSingle(IGNORED));
    }

    default Optional<T> single() {
        return Optional.ofNullable(getSingle());
    }

    default Optional<T> single(int offset) {
        return Optional.ofNullable(getSingle(offset));
    }

    default T getSingle() {
        return getSingle(IGNORED);
    }

    default T getSingle(int offset) {
        List<T> list = getList(offset, 2);
        Assert.state(list.size() <= 1, "found more than one");
        return list.isEmpty() ? null : list.get(0);
    }

    default List<T> getList(int offset) {
        return getList(offset, IGNORED);
    }

    default List<T> getList() {
        return getList(IGNORED, IGNORED);
    }

    default boolean exist() {
        return exist(IGNORED);
    }

    boolean exist(int offset);

}
