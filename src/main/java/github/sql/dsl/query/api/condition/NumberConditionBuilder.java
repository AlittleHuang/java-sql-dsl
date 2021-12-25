package github.sql.dsl.query.api.condition;

import github.sql.dsl.query.api.WhereClauses;

public interface NumberConditionBuilder<T, U extends Number> {

    NumberConditionBuilder<T, U> abs();

    NumberConditionBuilder<T, U> sum();

    NumberConditionBuilder<T, U> prod(U v);

    WhereClauses<T> eq(U value);

    WhereClauses<T> ge(U value);

    WhereClauses<T> gt(U value);

    WhereClauses<T> le(U value);

    WhereClauses<T> between(U a, U b);

    WhereClauses<T> lt(U value);

    WhereClauses<T> isNull();

    // NONE,
    // ABS,
    // SUM,
    // PROD,
    // DIFF,
    // QUOT,
    // MOD,
    // SQRT,
    // CONCAT,
    // SUBSTRING,
    // TRIM,
    // LOWER,
    // UPPER,
    // LENGTH,
    // LOCATE,
    // COALESCE,
    // NULLIF,
    // CUSTOMIZE,

}
