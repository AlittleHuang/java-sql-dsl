package github.sql.dsl.query.api;

import java.util.function.Function;

public interface Predicate<T> extends TypeQuery<T>, TypeQueryBuilder<T>, PredicateBuilder<T, Predicate<T>> {

    Predicate<T> andCombined(Function<Builder<T>, BooleanExpression> builder);

    Predicate<T> orCombined(Function<Builder<T>, BooleanExpression> builder);

    interface Builder<T> extends PredicateBuilder<T, Builder<T>> {
        BooleanExpression build();
    }
    
    

}
