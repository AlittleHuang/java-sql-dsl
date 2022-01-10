package github.sql.dsl.query.api.suport.builder.component;

import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.Operator;
import github.sql.dsl.query.api.expression.path.attribute.Attribute;
import lombok.Getter;

import java.util.Collection;
import java.util.function.Function;

@Getter
public class AbstractExpressionBuilder<T, U, NEXT> extends SubExpression<U> {

    protected final Function<SubPredicate, NEXT> mapper;

    public AbstractExpressionBuilder(Expression<U> expression,
                                     Operator combined,
                                     boolean negate,
                                     Function<SubPredicate, NEXT> mapper) {
        super(expression, combined, negate);
        this.mapper = mapper;
    }

    protected NEXT next(Operator operator, Object... value) {
        Expression<Boolean> then = expression.then(operator, value);
        return mapper.apply(new SubPredicate(then, combined, negate));
    }

    @SuppressWarnings("SameParameterValue")
    protected NEXT next(Operator operator, Collection<?> values) {
        Expression<Boolean> then = expression.then(operator, values);
        return mapper.apply(new SubPredicate(then, combined, negate));
    }

    public NEXT isNull() {
        return next(Operator.ISNULL);
    }

    public NEXT eq(U value) {
        return next(Operator.EQ, value);
    }

    public NEXT diff(U value) {
        return next(Operator.DIFF, value);
    }

    public NEXT in(Collection<U> values) {
        return next(Operator.IN, values);

    }

    public NEXT ge(Expression<U> value) {
        return next(Operator.GE, value);
    }

    public NEXT gt(Expression<U> value) {
        return next(Operator.GT, value);
    }

    public NEXT le(Expression<U> value) {
        return next(Operator.LE, value);
    }

    public NEXT between(Expression<U> a, Expression<U> b) {
        return next(Operator.BETWEEN, a, b);
    }

    public NEXT lt(Expression<U> value) {
        return next(Operator.LT, value);
    }


    public NEXT ge(U value) {
        return next(Operator.GE, value);
    }

    public NEXT gt(U value) {
        return next(Operator.GT, value);
    }

    public NEXT le(U value) {
        return next(Operator.LE, value);
    }

    public NEXT between(U a, U b) {
        return next(Operator.BETWEEN, a, b);
    }

    public NEXT lt(U value) {
        return next(Operator.LT, value);
    }

    public NEXT ge(Attribute<T, U> value) {
        return next(Operator.GE, value);
    }

    public NEXT gt(Attribute<T, U> value) {
        return next(Operator.GT, value);
    }

    public NEXT le(Attribute<T, U> value) {
        return next(Operator.LE, value);
    }

    public NEXT between(Attribute<T, U> a, Attribute<T, U> b) {
        return next(Operator.BETWEEN, a, b);
    }

    public NEXT lt(Attribute<T, U> value) {
        return next(Operator.LT, value);
    }

}
