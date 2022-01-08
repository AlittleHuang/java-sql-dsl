package github.sql.dsl.query.api.expression.path;

import github.sql.dsl.query.api.expression.path.attribute.NumberAttribute;

public class NumberPath<T, R extends Number>
        extends AttributePath<T, R>
        implements NumberAttribute<T, R> {
    public NumberPath(String... path) {
        super(path);
    }

}
