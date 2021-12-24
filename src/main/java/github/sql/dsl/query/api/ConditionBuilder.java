package github.sql.dsl.query.api;

public interface ConditionBuilder<T, U extends Comparable<?>> {

    WhereClauses<T> isNull();

    WhereClauses<T> notNull();

}
