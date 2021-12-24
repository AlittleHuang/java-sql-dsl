package github.sql.dsl.query.api;

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

    WhereClauses<T> notNull();

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
