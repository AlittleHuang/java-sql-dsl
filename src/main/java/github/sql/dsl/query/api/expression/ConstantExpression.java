package github.sql.dsl.query.api.expression;

import github.sql.dsl.query.suport.common.model.AbstractExpression;

public class ConstantExpression<T> extends AbstractExpression<T> {

    private final T value;

    public ConstantExpression(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
