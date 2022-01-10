package github.sql.dsl.criteria.query.expression;

public interface BooleanExpression extends Expression<Boolean> {

    default BooleanExpression not() {
        return of(then(Operator.NOT));
    }

    default BooleanExpression and(BooleanExpression expression) {
        return of(then(Operator.AND, expression));
    }

    default BooleanExpression or(BooleanExpression expression) {
        return of(then(Operator.OR, expression));
    }

    static BooleanExpression of(Expression<Boolean> value) {
        if (value instanceof BooleanExpression) {
            return ((BooleanExpression) value);
        }
        return new BooleanExpressionFace(value);
    }

}
