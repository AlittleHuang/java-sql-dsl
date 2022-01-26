package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.builder.combination.ComparablePredicateTester;
import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.Operator;
import github.sql.dsl.criteria.query.expression.path.AttributePath;
import github.sql.dsl.criteria.query.expression.path.attribute.ComparableAttribute;

import java.util.function.Function;

public class ComparablePredicateTesterImpl<T, U extends Comparable<?>, NEXT>
        extends AbstractExpressionBuilder<T, U, NEXT>
        implements ComparablePredicateTester<T, U, NEXT> {

    public ComparablePredicateTesterImpl(Expression<U> exchange,
                                         Operator combined,
                                         boolean negate,
                                         Function<SubPredicate, NEXT> mapper) {
        super(exchange, combined, negate, mapper);
    }

    @Override
    public NEXT ge(ComparableAttribute<T, U> value) {
        Expression<U> exchange = AttributePath.exchange(value);
        return super.ge(exchange);
    }

    @Override
    public NEXT gt(ComparableAttribute<T, U> value) {
        Expression<U> exchange = AttributePath.exchange(value);
        return super.gt(exchange);
    }

    @Override
    public NEXT le(ComparableAttribute<T, U> value) {
        Expression<U> exchange = AttributePath.exchange(value);
        return super.le(exchange);
    }

    @Override
    public NEXT between(ComparableAttribute<T, U> a, ComparableAttribute<T, U> b) {
        Expression<U> ea = AttributePath.exchange(a);
        Expression<U> eb = AttributePath.exchange(b);
        return super.between(ea, eb);
    }

    @Override
    public NEXT lt(ComparableAttribute<T, U> value) {
        Expression<U> exchange = AttributePath.exchange(value);
        return super.lt(exchange);
    }

}
