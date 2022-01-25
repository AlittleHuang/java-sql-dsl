package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.builder.combination.ComparablePredicateTester;
import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.Operator;
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
        return super.ge(value);
    }

    @Override
    public NEXT gt(ComparableAttribute<T, U> value) {
        return super.gt(value);
    }

    @Override
    public NEXT le(ComparableAttribute<T, U> value) {
        return super.le(value);
    }

    @Override
    public NEXT between(ComparableAttribute<T, U> a, ComparableAttribute<T, U> b) {
        return super.between(a, b);
    }

    @Override
    public NEXT lt(ComparableAttribute<T, U> value) {
        return super.lt(value);
    }

    @Override
    public ComparablePredicateTester<T, U, NEXT> nullIf(U value) {
        return new ComparablePredicateTesterImpl<>(
                expression.then(Operator.NULLIF, value),
                combined, negate, mapper
        );
    }
}
