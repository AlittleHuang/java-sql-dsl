package github.sql.dsl.criteria.query.expression;

import github.sql.dsl.criteria.query.support.builder.criteria.PredicateAssemblerImpl;
import lombok.experimental.Delegate;

public class PredicateBuilder<T> extends PredicateAssemblerImpl<T, Predicate.Builder<T>> implements Predicate.Builder<T> {

    public PredicateBuilder(Expression<Boolean> expression) {
        super(expression, PredicateBuilder::map);
    }

    private static <T> Predicate.Builder<T> map(Expression<Boolean> expression) {
        return new PredicateBuilder<>(expression);
    }

    @Delegate
    public Expression<Boolean> getExpression() {
        return expression;
    }

    @Override
    public Predicate<T> not() {
        return new PredicateBuilder<>(then(Operator.NOT));
    }

}
