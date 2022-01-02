package github.sql.dsl.query.api.expression.path;

import github.sql.dsl.query.api.expression.path.bridge.StringAttributeBridge;

public class StringPath<T>
        extends BridgePath<T, String>
        implements StringAttributeBridge<T> {
    public StringPath(String... path) {
        super(path);
    }
}
