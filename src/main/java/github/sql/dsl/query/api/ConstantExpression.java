package github.sql.dsl.query.api;


import java.util.List;

public class ConstantExpression<T> implements Expression<T> {

    private final T value;

    public ConstantExpression(T value) {
        this.value = value;
    }

    @Override
    public PathExpression<T> asPathExpression() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Type getType() {
        return Type.CONSTANT;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public Operator<T> getOperator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<? extends Expression<?>> getExpressions() {
        throw new UnsupportedOperationException();
    }
}
