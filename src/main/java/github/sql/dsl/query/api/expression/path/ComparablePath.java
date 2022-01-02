package github.sql.dsl.query.api.expression.path;

import github.sql.dsl.query.api.expression.path.bridge.ComparableAttributeBridge;

public class ComparablePath<T, R extends Comparable<?>>
        extends BridgePath<T, R>
        implements ComparableAttributeBridge<T, R> {
    public ComparablePath(String... path) {
        super(path);
    }

}
