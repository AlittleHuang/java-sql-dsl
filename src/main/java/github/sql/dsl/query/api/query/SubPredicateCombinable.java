package github.sql.dsl.query.api.query;

import github.sql.dsl.query.api.builder.PredicateCombinable;
import github.sql.dsl.query.api.expression.BooleanExpression;

public interface SubPredicateCombinable<T, NEXT>
        extends PredicateCombinable<T, SubPredicateCombinable<T, NEXT>> {

    BooleanExpression build();

}
