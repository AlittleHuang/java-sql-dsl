package github.sql.dsl.query.api.query;

import github.sql.dsl.query.api.expression.BooleanExpression;
import github.sql.dsl.query.api.builder.PredicateCombinable;

public interface SubPredicateCombinable<T, NEXT>
        extends PredicateCombinable<T, SubPredicateCombinable<T, NEXT>> {

    BooleanExpression build();

}
