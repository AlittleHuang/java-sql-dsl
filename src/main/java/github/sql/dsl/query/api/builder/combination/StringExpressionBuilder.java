package github.sql.dsl.query.api.builder.combination;

public interface StringExpressionBuilder<T, NEXT> extends ComparableExpressionBuilder<T, String, NEXT> {

    NEXT like(String value);

    NEXT startWith(String value);

    NEXT startEndWith(String value);

    NEXT contains(String value);

    StringExpressionBuilder<T, NEXT> lower();

    StringExpressionBuilder<T, NEXT> upper();

    StringExpressionBuilder<T, NEXT> substring(int a, int b);

    StringExpressionBuilder<T, NEXT> substring(int a);

    StringExpressionBuilder<T, NEXT> trim();

    NumberExpressionBuilder<T, Integer, NEXT> length();


}
