package github.sql.dsl.query.suport.builder.component;

import github.sql.dsl.query.api.expression.BooleanExpression;
import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.query.SubPredicateCombinable;
import github.sql.dsl.query.suport.builder.criteria.PredicateCombinableImpl;

import java.util.function.Function;

public class SubPredicateCombinableImpl<T, NEXT>
        extends PredicateCombinableImpl<T, SubPredicateCombinable<T, NEXT>>
        implements SubPredicateCombinable<T, NEXT> {

    private SubPredicate first;

    public SubPredicateCombinableImpl() {
        this(null);
    }

    public SubPredicateCombinableImpl(Expression<Boolean> expression) {
        this(expression, SubPredicateCombinableImpl::next);
    }

    public SubPredicateCombinableImpl(Expression<Boolean> expression,
                                      Function<Expression<Boolean>, SubPredicateCombinable<T, NEXT>> mapper) {
        super(expression, mapper);
    }

    private static <T, NEXT> SubPredicateCombinable<T, NEXT> next(Expression<Boolean> expression) {
        return new SubPredicateCombinableImpl<>(expression, SubPredicateCombinableImpl::next);
    }

    @Override
    protected SubPredicateCombinable<T, NEXT> mapperNext(SubPredicate subPredicate) {
        if (first == null) {
            this.first = subPredicate;
        }
        return super.mapperNext(subPredicate);
    }

    @Override
    protected SubPredicateCombinable<T, NEXT> next(SubPredicateCombinable<T, NEXT> next) {
        ((SubPredicateCombinableImpl<T, NEXT>) next).first = first;
        return super.next(next);
    }

    @Override
    public BooleanExpression build() {
        switch (expression.getType()) {
            case PATH:
                return BooleanPathExpression.fromPathExpression(expression);
            case CONSTANT:
                return BooleanConstantExpression.fromConstantExpression(expression);
            case OPERATOR:
                return new BooleanOperatorExpression(expression.getExpressions(), expression.getOperator());
        }
        throw new UnsupportedOperationException();
    }

}
