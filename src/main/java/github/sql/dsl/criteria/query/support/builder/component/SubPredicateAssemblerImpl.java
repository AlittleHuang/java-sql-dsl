package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.builder.combination.SubPredicateAssembler;
import github.sql.dsl.criteria.query.expression.BooleanExpression;
import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.support.builder.criteria.PredicateAssemblerImpl;

import java.util.function.Function;

public class SubPredicateAssemblerImpl<T, NEXT>
        extends PredicateAssemblerImpl<T, SubPredicateAssembler<T, NEXT>>
        implements SubPredicateAssembler<T, NEXT> {

    private SubPredicate first;

    public SubPredicateAssemblerImpl() {
        this(null);
    }

    public SubPredicateAssemblerImpl(Expression<Boolean> expression) {
        this(expression, SubPredicateAssemblerImpl::next);
    }

    public SubPredicateAssemblerImpl(Expression<Boolean> expression,
                                     Function<Expression<Boolean>, SubPredicateAssembler<T, NEXT>> mapper) {
        super(expression, mapper);
    }

    private static <T, NEXT> SubPredicateAssembler<T, NEXT> next(Expression<Boolean> expression) {
        return new SubPredicateAssemblerImpl<>(expression, SubPredicateAssemblerImpl::next);
    }

    @Override
    protected SubPredicateAssembler<T, NEXT> mapperNext(SubPredicate subPredicate) {
        if (first == null) {
            this.first = subPredicate;
        }
        return super.mapperNext(subPredicate);
    }

    @Override
    protected SubPredicateAssembler<T, NEXT> next(SubPredicateAssembler<T, NEXT> next) {
        ((SubPredicateAssemblerImpl<T, NEXT>) next).first = first;
        return super.next(next);
    }

    @Override
    public BooleanExpression build() {
        return BooleanExpression.of(expression.unwrap());
    }

}
