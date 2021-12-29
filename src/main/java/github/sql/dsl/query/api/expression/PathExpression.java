package github.sql.dsl.query.api.expression;

import github.sql.dsl.query.suport.common.model.AbstractExpression;
import lombok.Getter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Getter
public class PathExpression<T> extends AbstractExpression<T> {

    protected final List<String> path;

    public PathExpression(String... path) {
        this.path = Arrays.asList(path);
    }

    public PathExpression(List<String> path) {
        this.path = path;
    }

    public PathExpression<?> to(String... path) {
        return to(Arrays.asList(path));
    }


    public PathExpression<?> to(List<String> path) {
        String[] strings = Stream.of(this.path, path)
                .flatMap(List::stream)
                .toArray(String[]::new);
        return new PathExpression<>(strings);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathExpression<?> that = (PathExpression<?>) o;
        List<String> b = that.getPath();
        List<String> a = this.getPath();
        if (a == b)
            return true;
        if (a == null || b == null)
            return false;

        int length = a.size();
        if (b.size() != length)
            return false;

        Iterator<String> ia = a.iterator();
        Iterator<String> ib = b.iterator();
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
        int hashCode = 1;
        for (String e : getPath())
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        return hashCode;
    }

}
