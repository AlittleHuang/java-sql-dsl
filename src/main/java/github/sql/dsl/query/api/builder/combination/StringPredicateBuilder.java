package github.sql.dsl.query.api.builder.combination;

public interface StringPredicateBuilder<T, NEXT> extends ComparablePredicateBuilder<T, String, NEXT> {

    NEXT like(String value);

    NEXT startWith(String value);

    NEXT startEndWith(String value);

    NEXT contains(String value);

    StringPredicateBuilder<T, NEXT> lower();

    StringPredicateBuilder<T, NEXT> upper();

    StringPredicateBuilder<T, NEXT> substring(int a, int b);

    StringPredicateBuilder<T, NEXT> substring(int a);

    StringPredicateBuilder<T, NEXT> trim();

    NumberPredicateBuilder<T, Integer, NEXT> length();


}
