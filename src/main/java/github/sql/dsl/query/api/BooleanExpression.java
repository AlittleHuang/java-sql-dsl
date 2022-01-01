package github.sql.dsl.query.api;

public interface BooleanExpression extends Expression<Boolean> {

    BooleanExpression not();

}
