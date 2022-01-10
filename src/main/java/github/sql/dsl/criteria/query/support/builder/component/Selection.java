package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.expression.Expression;

public interface Selection<T> extends Expression<T> {

    AggregateFunction getAggregateFunction();

}
