package github.sql.dsl.query.api;

import github.sql.dsl.query.api.builder.WhereClausesBuilder;
import github.sql.dsl.query.api.expression.BooleanExpression;

import java.util.function.Function;

public interface WhereClauses<T> extends Results<T>, Criteria<T>, WhereClausesBuilder<T, WhereClauses<T>> {

    WhereClauses<T> andCombined(Function<Builder<T>, BooleanExpression> builder);

    WhereClauses<T> orCombined(Function<Builder<T>, BooleanExpression> builder);

    interface Builder<T> extends WhereClausesBuilder<T, Builder<T>> {
        BooleanExpression build();
    }
    
    

}
