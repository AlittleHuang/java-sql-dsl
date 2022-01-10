package github.sql.dsl.query.api.expression;

import github.sql.dsl.query.api.builder.PredicateCombinable;
import github.sql.dsl.query.api.builder.combination.ComparablePredicateBuilder;
import github.sql.dsl.query.api.builder.combination.NumberPredicateBuilder;
import github.sql.dsl.query.api.builder.combination.PredicateBuilder;
import github.sql.dsl.query.api.builder.combination.StringPredicateBuilder;
import github.sql.dsl.query.api.expression.path.AttributePath;
import github.sql.dsl.query.api.expression.path.attribute.*;
import github.sql.dsl.query.api.suport.builder.component.*;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public interface Predicate<T> extends PredicateCombinable<T, Predicate<T>>, Expression<Boolean> {

    default Predicate<T> not() {
        throw new UnsupportedOperationException();
    }

    static <T, R> @NotNull PredicateBuilder<T, R, Predicate<T>> get(Attribute<T, R> attribute) {
        return new PredicateBuilderSupport<>(AttributePath.exchange(attribute), Operator.AND, false,
                (SubPredicate subPredicate) -> new PredicateFace<>(subPredicate.getExpression())
        );
    }

    static <T, R extends Number> NumberPredicateBuilder<T, R, Predicate<T>> get(NumberAttribute<T, R> attribute) {
        return new NumberPredicateBuilderSupport<>(AttributePath.exchange(attribute), Operator.AND, false,
                (SubPredicate subPredicate) -> new PredicateFace<>(subPredicate.getExpression())
        );
    }


    static <T> StringPredicateBuilder<T, Predicate<T>> get(StringAttribute<T> attribute) {
        return new StringPredicateBuilderSupport<>(AttributePath.exchange(attribute), Operator.AND, false,
                (SubPredicate subPredicate) -> new PredicateFace<>(subPredicate.getExpression())
        );
    }


    static <T, R extends Date> ComparablePredicateBuilder<T, R, Predicate<T>> get(ComparableAttribute<T, R> attribute) {
        return new ComparablePredicateBuilderSupport<>(AttributePath.exchange(attribute), Operator.AND, false,
                (SubPredicate subPredicate) -> new PredicateFace<>(subPredicate.getExpression())
        );
    }

    static <T> Predicate<T> get(BooleanAttribute<T> attribute) {
        return new PredicateFace<>(AttributePath.exchange(attribute));
    }


}
