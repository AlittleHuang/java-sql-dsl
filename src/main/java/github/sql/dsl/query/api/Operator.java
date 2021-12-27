package github.sql.dsl.query.api;

import lombok.Getter;

@Getter
public final class Operator<T> {

    public static final Operator<Boolean> AND = new Operator<>("and");
    public static final Operator<Boolean> OR = new Operator<>("or");
    public static final Operator<Boolean> NOT = new Operator<>("not");
    public static final Operator<Boolean> GT = new Operator<>(">");
    public static final Operator<Boolean> EQ = new Operator<>("=");
    public static final Operator<Boolean> DIF = new Operator<>("<>");
    public static final Operator<Boolean> GE = new Operator<>(">=");
    public static final Operator<Boolean> LT = new Operator<>("<");
    public static final Operator<Boolean> LE = new Operator<>("<=");

    public static final Operator<Boolean> LIKE = new Operator<>("like");

    public static final Operator<String> LOWER = new Operator<>("lower");
    public static final Operator<String> UPPER = new Operator<>("upper");
    public static final Operator<String> SUBSTRING = new Operator<>("substring");
    public static final Operator<String> TRIM = new Operator<>("trim");
    public static final Operator<String> LENGTH = new Operator<>("length");

    public static final Operator<Number> ADD = new Operator<>("+");
    public static final Operator<Number> SUBTRACT = new Operator<>("-");
    public static final Operator<Number> MULTIPLY = new Operator<>("*");
    public static final Operator<Number> DIVIDE = new Operator<>("/");
    public static final Operator<Number> MOD = new Operator<>("mod");

    public static final Operator<Object> NULLIF = new Operator<>("nullif");
    public static final Operator<Boolean> ISNULL = new Operator<>("isnull");
    public static final Operator<Boolean> IN = new Operator<>("in");
    public static final Operator<Boolean> BETWEEN = new Operator<>("between");


    private final String name;

    private Operator(String name) {
        this.name = name;
    }


}
