package github.sql.dsl.criteria.query.expression;

import github.sql.dsl.criteria.query.builder.PredicateAssembler;
import github.sql.dsl.criteria.query.builder.combination.ComparablePredicateTester;
import github.sql.dsl.criteria.query.builder.combination.NumberPredicateTester;
import github.sql.dsl.criteria.query.builder.combination.PredicateTester;
import github.sql.dsl.criteria.query.builder.combination.StringPredicateTester;
import github.sql.dsl.criteria.query.expression.path.AttributePath;
import github.sql.dsl.criteria.query.expression.path.attribute.*;
import github.sql.dsl.criteria.query.support.builder.component.*;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public interface Predicate<T> extends Expression<Boolean> {

    Predicate<T> not();

    Predicate<T> and(Predicate<T> predicate);

    Predicate<T> or(Predicate<T> predicate);

    interface Builder<T> extends PredicateAssembler<T, Builder<T>>, Predicate<T> {

    }

    static <T, R> @NotNull PredicateTester<T, R, Predicate.Builder<T>> get(Attribute<T, R> attribute) {
        return new PredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.AND, false,
                (SubPredicate subPredicate) -> new PredicateBuilder<>(subPredicate.getExpression())
        );
    }

    static <T, R extends Number> NumberPredicateTester<T, R, Predicate.Builder<T>> get(NumberAttribute<T, R> attribute) {
        return new NumberPredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.AND, false,
                (SubPredicate subPredicate) -> new PredicateBuilder<>(subPredicate.getExpression())
        );
    }

    static <T> StringPredicateTester<T, Predicate.Builder<T>> get(StringAttribute<T> attribute) {
        return new StringPredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.AND, false,
                (SubPredicate subPredicate) -> new PredicateBuilder<>(subPredicate.getExpression())
        );
    }

    static <T, R extends Date> ComparablePredicateTester<T, R, Predicate.Builder<T>> get(ComparableAttribute<T, R> attribute) {
        return new ComparablePredicateTesterImpl<>(AttributePath.exchange(attribute), Operator.AND, false,
                (SubPredicate subPredicate) -> new PredicateBuilder<>(subPredicate.getExpression())
        );
    }

    static <T> Predicate.Builder<T> get(BooleanAttribute<T> attribute) {
        return new PredicateBuilder<>(AttributePath.exchange(attribute));
    }


}
