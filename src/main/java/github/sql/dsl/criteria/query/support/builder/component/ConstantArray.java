package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.util.Array;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Stream;

public final class ConstantArray<T> implements Array<T> {

    private final Object[] values;

    private ConstantArray(Object[] values, boolean copy) {
        if (copy) {
            this.values = new Object[values.length];
            System.arraycopy(values, 0, (this.values), 0, values.length);
        } else {
            this.values = values;
        }

    }

    @SuppressWarnings("unchecked")
    public ConstantArray(T... values) {
        this(Objects.requireNonNull(values), true);
    }

    public ConstantArray(List<? extends T> values) {
        this.values = values.toArray();
    }

    public static <T> ConstantArray<T> from(Array<T> array) {
        if (array instanceof ConstantArray) {
            return (ConstantArray<T>) array;
        } else {
            return new ConstantArray<>(array.asList());
        }
    }

    public int length() {
        return values.length;
    }

    public ConstantArray<T> concat(T t) {
        Object[] objects = new Object[values.length + 1];
        System.arraycopy(values, 0, objects, 0, values.length);
        objects[values.length] = t;
        return new ConstantArray<>(objects, false);
    }

    public ConstantArray<T> concat(Collection<? extends T> collection) {
        if (collection == null || collection.isEmpty()) {
            return this;
        }
        Object[] objects = new Object[values.length + collection.size()];
        System.arraycopy(values, 0, objects, 0, values.length);
        int index = values.length;
        for (T t : collection) {
            objects[index++] = t;
        }
        return new ConstantArray<>(objects, false);
    }

    @Override
    public T get(int index) {
        //noinspection unchecked
        return (T) values[index];
    }

    @Override
    public boolean isEmpty() {
        return length() == 0;
    }

    @Override
    public List<T> asList() {
        //noinspection unchecked
        return (List<T>) Arrays.asList(values);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        //noinspection unchecked
        return (Iterator<T>) Stream.of(values).iterator();
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }

}
