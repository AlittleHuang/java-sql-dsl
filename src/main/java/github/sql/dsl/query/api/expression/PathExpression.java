package github.sql.dsl.query.api.expression;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PathExpression<T> implements Expression<T>, Iterable<String> {

    protected final String[] path;
    protected int length;

    public PathExpression(String... path) {
        if (path == null || path.length == 0) {
            throw new IllegalArgumentException();
        }
        this.path = path;
        this.length = this.path.length;
    }

    protected PathExpression(String[] path, int length) {
        this.path = path;
        this.length = length;
    }

    public PathExpression(PathExpression<?> path) {
        this.path = path.path;
        this.length = path.length;
    }

    @Override
    @NotNull
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < length;
            }

            @Override
            public String next() {
                return path[index++];
            }

        };
    }


    public int size() {
        return path.length;
    }

    public String get(int index) {
        return path[index];
    }

    public PathExpression<?> parent() {
        int length = this.length - 1;
        if (length <= 0) {
            return null;
        }
        return new PathExpression<>(path, length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PathExpression)) {
            return false;
        }
        PathExpression<?> that = (PathExpression<?>) o;

        int length = this.size();
        if (that.size() != length) {
            return false;
        }
        Iterator<String> ia = this.iterator();
        Iterator<String> ib = that.iterator();
        while (ia.hasNext()) {
            if (!ib.hasNext()) {
                return false;
            }
            if (!Objects.equals(ia.next(), ib.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(path);
    }

    public PathExpression<?> to(String path) {
        String[] values = Stream.concat(Arrays.stream(this.path), Stream.of(path))
                .toArray(String[]::new);
        return new PathExpression<>(values);
    }

    public static <T> PathExpression<T> fromPath(Expression<T> path) {
        if (path instanceof PathExpression) {
            return (PathExpression<T>) path;
        }
        PathExpression<T> pathExpression = path.asPathExpression();
        return new PathExpression<>(pathExpression.path, pathExpression.length);
    }

    @Override
    public PathExpression<T> asPathExpression() {
        return this;
    }

    @SuppressWarnings("SameParameterValue")
    protected void arraycopy(int srcPos, String[] path, int destPos, int length) {
        if (length >= 0) {
            System.arraycopy(this.path, srcPos, path, destPos, length);
        }
    }

    @Override
    public String toString() {
        int iMax = length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(path[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    public Type getType() {
        return Type.PATH;
    }

    public T getValue() {
        throw new UnsupportedOperationException();
    }

    public Operator getOperator() {
        throw new UnsupportedOperationException();
    }

    public List<? extends Expression<?>> getExpressions() {
        throw new UnsupportedOperationException();
    }
}
