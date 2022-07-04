package github.sql.dsl.criteria.query.expression;

public interface OperatorExpression<T> extends SqlExpression<T> {

    default PathExpression<T> asPathExpression() {
        throw new UnsupportedOperationException();
    }

    default Type getType() {
        return Type.OPERATOR;
    }

    default T getValue() {
        throw new UnsupportedOperationException();
    }

}
