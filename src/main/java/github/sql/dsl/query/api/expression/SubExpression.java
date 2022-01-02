package github.sql.dsl.query.api.expression;

public interface SubExpression<T> {

    Expression<T> getValue();

    Boolean isNegate();

    Operator getOperator();

}
