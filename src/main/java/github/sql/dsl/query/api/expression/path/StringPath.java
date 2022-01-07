package github.sql.dsl.query.api.expression.path;

import github.sql.dsl.query.api.expression.path.bridge.StringAttribute;

public class StringPath<T>
        extends AttributePath<T, String>
        implements StringAttribute<T> {
    public StringPath(String... path) {
        super(path);
    }
}
