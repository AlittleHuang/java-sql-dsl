package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.builder.combination.PredicateTester;
import github.sql.dsl.criteria.query.expression.ConstantExpression;
import github.sql.dsl.criteria.query.expression.SqlExpression;
import github.sql.dsl.criteria.query.expression.Operator;
import lombok.Getter;

import java.util.Collection;
import java.util.function.Function;

@Getter
public class AbstractExpressionBuilder<T, U, NEXT> extends SubExpression<U> {

    protected final Function<SubPredicate, NEXT> mapper;

    public AbstractExpressionBuilder(SqlExpression<U> expression,
                                     Operator combined,
                                     boolean negate,
                                     Function<SubPredicate, NEXT> mapper) {
        super(expression, combined, negate);
        this.mapper = mapper;
    }

    protected NEXT next(Operator operator, Object... value) {
        SqlExpression<Boolean> then = expression.then(operator, value);
        return mapper.apply(new SubPredicate(then, combined, negate));
    }

    protected NEXT next(Operator operator, Collection<?> values) {
        return next(operator, values.toArray());
    }

    public NEXT isNull() {
        return next(Operator.ISNULL);
    }

    public NEXT eq(U value) {
        return next(Operator.EQ, value);
    }

    public NEXT ne(U value) {
        return next(Operator.NE, value);
    }

    public NEXT in(Collection<U> values) {
        return next(Operator.IN, values);
    }

    public NEXT ge(SqlExpression<U> value) {
        return next(Operator.GE, value);
    }

    public NEXT gt(SqlExpression<U> value) {
        return next(Operator.GT, value);
    }

    public NEXT le(SqlExpression<U> value) {
        return next(Operator.LE, value);
    }

    public NEXT between(SqlExpression<U> a, SqlExpression<U> b) {
        return next(Operator.BETWEEN, a, b);
    }

    public NEXT lt(SqlExpression<U> value) {
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

    public PredicateTester<T, U, NEXT> nullIf(U value) {
        SqlExpression<U> expression = this.expression.then(Operator.NULLIF, new ConstantExpression<>(value));
        return new PredicateTesterImpl<>(expression, combined, negate, mapper);
    }

    public PredicateTester<T, U, NEXT> ifNull(U value) {
        SqlExpression<U> expression = this.expression.then(Operator.IF_NULL, new ConstantExpression<>(value));
        return new PredicateTesterImpl<>(expression, combined, negate, mapper);
    }

}
