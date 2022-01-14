package github.sql.dsl.criteria.query.builder.combination;

import github.sql.dsl.criteria.query.builder.PredicateAssembler;
import github.sql.dsl.criteria.query.expression.BooleanExpression;

public interface SubPredicateAssembler<T, NEXT>
        extends PredicateAssembler<T, SubPredicateAssembler<T, NEXT>> {

    BooleanExpression build();

}
