package github.sql.dsl.criteria.query.expression.path;

import github.sql.dsl.criteria.query.expression.path.attribute.EntityAttribute;

public class EntityPath<T, R extends Entity>
        extends AttributePath<T, R>
        implements EntityAttribute<T, R> {
    public EntityPath(String... path) {
        super(path);
    }

}
