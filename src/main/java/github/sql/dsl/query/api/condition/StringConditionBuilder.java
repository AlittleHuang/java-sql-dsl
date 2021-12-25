package github.sql.dsl.query.api.condition;

import github.sql.dsl.query.api.WhereClauses;
import github.sql.dsl.query.api.condition.ComparableConditionBuilder;

public interface StringConditionBuilder<T> extends ComparableConditionBuilder<T, String> {

    WhereClauses<T> like(String value);

    WhereClauses<T> startWith(String value);

    WhereClauses<T> startEndWith(String value);

    WhereClauses<T> contains(String value);

}
