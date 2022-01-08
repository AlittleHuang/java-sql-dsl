package github.sql.dsl.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Array<T> extends Iterable<T> {

    Array<T> concat(T t);

    Array<T> concat(Collection<? extends T> collection);

    T get(int index);

    boolean isEmpty();

    List<T> asList();

    default Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

}