package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.builder.PredicateCombinable;
import github.sql.dsl.criteria.query.expression.BooleanExpression;

public interface SubPredicateCombinable<T, NEXT>
        extends PredicateCombinable<T, SubPredicateCombinable<T, NEXT>> {

    BooleanExpression build();

}
