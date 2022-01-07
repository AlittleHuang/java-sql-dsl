package github.sql.dsl.query.suport.builder.criteria;

import github.sql.dsl.query.api.builder.Projectable;
import github.sql.dsl.query.api.query.ProjectionQuery;

public class ProjectableImpl<T> implements Projectable<T> {

    @Override
    public <R> ProjectionQuery<R> projected(Class<R> projectionType) {
        return null;
    }

}
