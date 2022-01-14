package github.sql.dsl.criteria.query.builder.combination;

public interface StringPredicateTester<T, NEXT> extends ComparablePredicateTester<T, String, NEXT> {

    NEXT like(String value);

    NEXT startWith(String value);

    NEXT startEndWith(String value);

    NEXT contains(String value);

    StringPredicateTester<T, NEXT> lower();

    StringPredicateTester<T, NEXT> upper();

    StringPredicateTester<T, NEXT> substring(int a, int b);

    StringPredicateTester<T, NEXT> substring(int a);

    StringPredicateTester<T, NEXT> trim();

    NumberPredicateTester<T, Integer, NEXT> length();


}
