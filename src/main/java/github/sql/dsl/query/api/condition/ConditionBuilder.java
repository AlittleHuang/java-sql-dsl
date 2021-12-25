package github.sql.dsl.query.api.condition;

import github.sql.dsl.query.api.WhereClauses;

public interface ConditionBuilder<T, U> {

    WhereClauses<T> isNull();

}
