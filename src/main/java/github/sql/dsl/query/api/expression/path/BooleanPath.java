package github.sql.dsl.query.api.expression.path;

import github.sql.dsl.query.api.expression.path.bridge.BooleanAttributeBridge;

public class BooleanPath<T>
        extends BridgePath<T, Boolean>
        implements BooleanAttributeBridge<T> {
    public BooleanPath(String... path) {
        super(path);
    }
}
