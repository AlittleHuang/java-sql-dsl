package github.sql.dsl.query.api.expression.path;

import github.sql.dsl.query.api.expression.path.bridge.BooleanAttribute;

public class BooleanPath<T>
        extends AttributePath<T, Boolean>
        implements BooleanAttribute<T> {
    public BooleanPath(String... path) {
        super(path);
    }
}
