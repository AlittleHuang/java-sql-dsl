package github.sql.dsl.query.suport.builder.component;

import github.sql.dsl.query.api.expression.Expression;

public interface Selection<T> extends Expression<T> {

    AggregateFunction getAggregateFunction();

}
