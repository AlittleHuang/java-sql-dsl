package github.sql.dsl.query.suport;

import github.sql.dsl.query.api.query.ObjectsTypeQuery;
import github.sql.dsl.query.api.query.TypeQuery;

public interface ResultsFactory {

    <T> TypeQuery<T> results(CriteriaQuery criteriaQuery, Class<T> type);

    ObjectsTypeQuery arrayResults(CriteriaQuery criteriaQuery, Class<?> type);


}
