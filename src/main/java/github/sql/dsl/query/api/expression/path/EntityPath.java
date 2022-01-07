package github.sql.dsl.query.api.expression.path;

import github.sql.dsl.query.api.expression.path.bridge.EntityAttribute;

public class EntityPath<T, R extends Entity>
        extends AttributePath<T, R>
        implements EntityAttribute<T, R> {
    public EntityPath(String... path) {
        super(path);
    }

}
