package github.sql.dsl.query.api.expression.path;

import github.sql.dsl.query.api.expression.path.bridge.EntityAttributeBridge;

public class EntityPath<T, R extends Entity>
        extends BridgePath<T, R>
        implements EntityAttributeBridge<T, R> {
    public EntityPath(String... path) {
        super(path);
    }

}
