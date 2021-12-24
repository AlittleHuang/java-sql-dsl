package github.sql.dsl.query.api;

public interface Query<T> extends Criteria<T> {

    WhereClauses<T> where();

}
