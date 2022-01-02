package github.sql.dsl.query.suport.builder.component;

import github.sql.dsl.util.Array;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class ConstantList<T> implements Array<T> {

    private final List<T> values;
    private final int size;

    @SafeVarargs
    public ConstantList(T... values) {
        this.values = new ArrayList<>();
        Collections.addAll(this.values, values);
        size = this.values.size();
    }

    public ConstantList() {
        this.values = new ArrayList<>();
        size = 0;
    }

    public ConstantList(int initialCapacity) {
        this.values = new ArrayList<>(initialCapacity);
        size = 0;
    }

    public int size() {
        return size;
    }

    public ConstantList(List<? extends T> values) {
        this.values = new ArrayList<>(values);
        this.size = values.size();
    }

    @Override
    public ConstantList<T> concat(T t) {
        values.add(t);
        return new ConstantList<>(values);
    }

    @Override
    public ConstantList<T> concat(Collection<? extends T> collection) {
        values.addAll(collection);
        return new ConstantList<>(values);
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return values.get(index);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public List<T> asList() {
        ArrayList<T> result = new ArrayList<>(size);
        for (T t : this) {
            result.add(t);
        }
        return result;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return values.get(index++);
            }
        };
    }

    @Override
    public String toString() {
        int iMax = size - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(values.get(i));
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

}
