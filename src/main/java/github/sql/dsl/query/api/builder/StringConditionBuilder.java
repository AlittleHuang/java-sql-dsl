package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.WhereClauses;

public interface StringConditionBuilder<T> extends ComparableConditionBuilder<T, String> {

    WhereClauses<T> like(String value);

    WhereClauses<T> startWith(String value);

    WhereClauses<T> startEndWith(String value);

    WhereClauses<T> contains(String value);

    @Override
    StringConditionBuilder<T> nullIf(String value);

    StringConditionBuilder<T> lower();

    StringConditionBuilder<T> upper();

    StringConditionBuilder<T> substring();

    StringConditionBuilder<T> trim();

    NumberConditionBuilder<T, Integer> length();



}
