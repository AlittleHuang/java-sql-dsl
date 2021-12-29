package github.sql.dsl.query.api.expression;

public interface StringExpression extends Expression<String> {

    StringExpression lower();

    StringExpression upper();

    StringExpression substring();

    StringExpression trim();

    NumberExpression<Integer> length();

}
