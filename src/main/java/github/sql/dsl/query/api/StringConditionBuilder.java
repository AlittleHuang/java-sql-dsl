package github.sql.dsl.query.api;

public interface StringConditionBuilder<T> extends ComparableConditionBuilder<T, String> {

    WhereClauses<T> like(String value);

    WhereClauses<T> startWith(String value);

    WhereClauses<T> startEndWith(String value);

    WhereClauses<T> contains(String value);

}
