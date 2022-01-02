package github.sql.dsl.query.api.expression.path;

import github.sql.dsl.query.api.expression.path.bridge.NumberAttributeBridge;

public class NumberPath<T, R extends Number>
        extends BridgePath<T, R>
        implements NumberAttributeBridge<T, R> {
    public NumberPath(String... path) {
        super(path);
    }

}
