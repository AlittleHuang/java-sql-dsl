package github.sql.dsl.criteria.query.expression;

import github.sql.dsl.criteria.query.support.builder.criteria.PredicateCombinableImpl;
import lombok.experimental.Delegate;

public class PredicateFace<T> extends PredicateCombinableImpl<T, Predicate<T>> implements Predicate<T> {

    public PredicateFace(Expression<Boolean> expression) {
        super(expression, PredicateFace::map);
    }

    private static <T> Predicate<T> map(Expression<Boolean> expression) {
        return new PredicateFace<>(expression);
    }

    @Delegate
    public Expression<Boolean> getExpression() {
        return expression;
    }

    @Override
    public Predicate<T> not() {
        return new PredicateFace<>(unwrap().then(Operator.NOT));
    }

}
