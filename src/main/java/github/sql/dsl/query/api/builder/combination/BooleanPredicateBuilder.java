package github.sql.dsl.query.api.builder.combination;

public interface BooleanPredicateBuilder<T, NEXT> extends PredicateBuilder<T, Boolean, NEXT> {

    NEXT not();

}
