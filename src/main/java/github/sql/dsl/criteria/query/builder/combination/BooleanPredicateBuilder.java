package github.sql.dsl.criteria.query.builder.combination;

public interface BooleanPredicateBuilder<T, NEXT> extends PredicateBuilder<T, Boolean, NEXT> {

    NEXT not();

}
