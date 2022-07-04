package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.builder.combination.PredicateTester;
import github.sql.dsl.criteria.query.expression.SqlExpression;
import github.sql.dsl.criteria.query.expression.Operator;

import java.util.function.Function;

public class PredicateTesterImpl<T, U, NEXT>
        extends AbstractExpressionBuilder<T, U, NEXT>
        implements PredicateTester<T, U, NEXT> {

    public PredicateTesterImpl(SqlExpression<U> expression,
                               Operator combined,
                               boolean negate,
                               Function<SubPredicate, NEXT> mapper) {
        super(expression, combined, negate, mapper);
    }


}
