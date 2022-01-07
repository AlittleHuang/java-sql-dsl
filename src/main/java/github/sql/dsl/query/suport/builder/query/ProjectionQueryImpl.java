package github.sql.dsl.query.suport.builder.query;

import github.sql.dsl.query.api.query.ProjectionQuery;
import github.sql.dsl.query.suport.TypeQueryFactory;

import java.util.List;

public class ProjectionQueryImpl<T, R> implements ProjectionQuery<R> {

    protected final TypeQueryFactory typeQueryFactory;
    protected final Class<T> entityType;
    protected final CriteriaQueryImpl criteriaQuery;
    protected final Class<R> projectionType;

    public ProjectionQueryImpl(TypeQueryFactory typeQueryFactory,
                               Class<T> entityType,
                               CriteriaQueryImpl criteriaQuery,
                               Class<R> projectionType) {
        this.typeQueryFactory = typeQueryFactory;
        this.entityType = entityType;
        this.criteriaQuery = criteriaQuery;
        this.projectionType = projectionType;
    }

    @Override
    public List<R> getResultList(int offset, int maxResul) {
        return typeQueryFactory
                .getProjectionQuery(criteriaQuery, entityType, projectionType)
                .getResultList(offset, maxResul);
    }
}
