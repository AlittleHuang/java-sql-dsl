package github.sql.dsl.criteria.query.support.builder.component;

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
        if (size == values.size()) {
            values.add(t);
            return new ConstantList<>(values);
        } else {
            ArrayList<T> list = new ArrayList<>(size + 1);
            for (T v : this) {
                list.add(v);
            }
            list.add(t);
            return new ConstantList<>(list);
        }
    }

    @Override
    public ConstantList<T> concat(Collection<? extends T> collection) {
        if (size == values.size()) {
            values.addAll(collection);
            return new ConstantList<>(values);
        } else {
            ArrayList<T> list = new ArrayList<>(size + collection.size());
            for (T t : this) {
                list.add(t);
            }
            list.addAll(collection);
            return new ConstantList<>(list);
        }
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
