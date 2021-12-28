package github.sql.dsl.query.api;

import lombok.Getter;

@Getter
public final class Operator<T> {

    public static final Operator<Boolean> NOT = new Operator<>("not", 10);
    public static final Operator<Boolean> AND = new Operator<>("and", 11);
    public static final Operator<Boolean> OR = new Operator<>("or", 13);
    public static final Operator<Boolean> GT = new Operator<>(">", 8);
    public static final Operator<Boolean> EQ = new Operator<>("=", 8);
    public static final Operator<Boolean> DIFF = new Operator<>("<>", 8);
    public static final Operator<Boolean> GE = new Operator<>(">=", 8);
    public static final Operator<Boolean> LT = new Operator<>("<", 8);
    public static final Operator<Boolean> LE = new Operator<>("<=", 8);

    public static final Operator<Boolean> LIKE = new Operator<>("like", 8);

    public static final Operator<String> LOWER = new Operator<>("lower", 0);
    public static final Operator<String> UPPER = new Operator<>("upper", 0);
    public static final Operator<String> SUBSTRING = new Operator<>("substring", 0);
    public static final Operator<String> TRIM = new Operator<>("trim", 0);
    public static final Operator<String> LENGTH = new Operator<>("length", 0);

    public static final Operator<Number> ADD = new Operator<>("+", 4);
    public static final Operator<Number> SUBTRACT = new Operator<>("-", 4);
    public static final Operator<Number> MULTIPLY = new Operator<>("*", 3);
    public static final Operator<Number> DIVIDE = new Operator<>("/", 3);
    public static final Operator<Number> MOD = new Operator<>("mod", 3);

    public static final Operator<Object> NULLIF = new Operator<>("nullif", 0);
    public static final Operator<Boolean> ISNULL = new Operator<>("isnull", 0);
    public static final Operator<Boolean> IN = new Operator<>("in", 0);
    public static final Operator<Boolean> BETWEEN = new Operator<>("between", 0);


    private final String sign;
    private final int precedence;

    private Operator(String sign, int priority) {
        this.sign = sign;
        this.precedence = priority;
    }


    @Override
    public String toString() {
        return sign;
    }
}
