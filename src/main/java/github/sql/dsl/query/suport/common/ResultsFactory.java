package github.sql.dsl.query.suport.common;

import github.sql.dsl.query.api.ObjectsTypeQuery;
import github.sql.dsl.query.api.TypeQuery;
import github.sql.dsl.query.suport.common.model.CriteriaQuery;

public interface ResultsFactory {

    <T> TypeQuery<T> results(CriteriaQuery criteriaQuery, Class<T> type);

    ObjectsTypeQuery arrayResults(CriteriaQuery criteriaQuery, Class<?> type);


}
