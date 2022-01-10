package github.sql.dsl.query.api.suport.builder.component;

import github.sql.dsl.query.api.builder.combination.SubPredicateCombinable;
import github.sql.dsl.query.api.expression.BooleanExpression;
import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.suport.builder.criteria.PredicateCombinableImpl;

import java.util.function.Function;

public class SubPredicateCombinableSupport<T, NEXT>
        extends PredicateCombinableImpl<T, SubPredicateCombinable<T, NEXT>>
        implements SubPredicateCombinable<T, NEXT> {

    private SubPredicate first;

    public SubPredicateCombinableSupport() {
        this(null);
    }

    public SubPredicateCombinableSupport(Expression<Boolean> expression) {
        this(expression, SubPredicateCombinableSupport::next);
    }

    public SubPredicateCombinableSupport(Expression<Boolean> expression,
                                         Function<Expression<Boolean>, SubPredicateCombinable<T, NEXT>> mapper) {
        super(expression, mapper);
    }

    private static <T, NEXT> SubPredicateCombinable<T, NEXT> next(Expression<Boolean> expression) {
        return new SubPredicateCombinableSupport<>(expression, SubPredicateCombinableSupport::next);
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
        ((SubPredicateCombinableSupport<T, NEXT>) next).first = first;
        return super.next(next);
    }

    @Override
    public BooleanExpression build() {
        return BooleanExpression.of(expression.unwrap());
    }

}
