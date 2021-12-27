package github.sql.dsl.query.api;

import github.sql.dsl.query.api.builder.WhereClausesBuilder;
import github.sql.dsl.query.suport.common.expression.OperatorExpression;

import java.util.function.Function;

public interface WhereClauses<T> extends Results<T>, Criteria<T>, WhereClausesBuilder<T, WhereClauses<T>> {

    WhereClauses<T> andCombined(Function<Builder<T>, OperatorExpression<Boolean>> builder);

    WhereClauses<T> orCombined(Function<Builder<T>, OperatorExpression<Boolean>> builder);

    WhereClauses<T> orCombinedNot(Function<Builder<T>, OperatorExpression<Boolean>> builder);

    WhereClauses<T> andCombinedNot(Function<Builder<T>, OperatorExpression<Boolean>> builder);

    interface Builder<T> extends WhereClausesBuilder<T, Builder<T>> {
        OperatorExpression<Boolean> build();
    }
    
    

}
