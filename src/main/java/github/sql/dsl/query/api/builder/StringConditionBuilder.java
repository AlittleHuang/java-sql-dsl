package github.sql.dsl.query.api.builder;

public interface StringConditionBuilder<T, V> extends ComparableConditionBuilder<T, String, V> {

    V like(String value);

    V startWith(String value);

    V startEndWith(String value);

    V contains(String value);

    @Override
    StringConditionBuilder<T, V> nullIf(String value);

    StringConditionBuilder<T, V> lower();

    StringConditionBuilder<T, V> upper();

    StringConditionBuilder<T, V> substring(int a, int b);

    StringConditionBuilder<T, V> substring(int a);

    StringConditionBuilder<T, V> trim();

    NumberConditionBuilder<T, Integer, V> length();


}
