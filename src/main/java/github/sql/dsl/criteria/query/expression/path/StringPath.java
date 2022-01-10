package github.sql.dsl.criteria.query.expression.path;

import github.sql.dsl.criteria.query.expression.path.attribute.StringAttribute;

public class StringPath<T>
        extends AttributePath<T, String>
        implements StringAttribute<T> {
    public StringPath(String... path) {
        super(path);
    }
}
