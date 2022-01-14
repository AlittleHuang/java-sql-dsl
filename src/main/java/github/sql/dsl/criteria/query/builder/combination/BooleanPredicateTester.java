package github.sql.dsl.criteria.query.builder.combination;

public interface BooleanPredicateTester<T, NEXT> extends PredicateTester<T, Boolean, NEXT> {

    NEXT not();

}
