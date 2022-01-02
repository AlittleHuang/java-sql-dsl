package github.sql.dsl.query.api.expression;

public interface BooleanExpression extends Expression<Boolean> {

    BooleanExpression not();

}
