package github.sql.dsl.query.api;

public interface StringExpressionBuilder<T, V> extends ComparableExpressionBuilder<T, String, V> {

    V like(String value);

    V startWith(String value);

    V startEndWith(String value);

    V contains(String value);

    @Override
    StringExpressionBuilder<T, V> nullIf(String value);

    StringExpressionBuilder<T, V> lower();

    StringExpressionBuilder<T, V> upper();

    StringExpressionBuilder<T, V> substring(int a, int b);

    StringExpressionBuilder<T, V> substring(int a);

    StringExpressionBuilder<T, V> trim();

    NumberExpressionBuilder<T, Integer, V> length();


}
