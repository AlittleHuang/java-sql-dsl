package github.sql.dsl.criteria.query.expression;

import lombok.Getter;

@Getter
public enum Operator {

    NOT("not", 10),
    AND("and", 11),
    OR("or", 13),
    GT(">", 8),
    EQ("=", 8),
    DIFF("<>", 8),
    GE(">=", 8),
    LT("<", 8),
    LE("<=", 8),

    LIKE("like", 8),

    LOWER("lower", 0),
    UPPER("upper", 0),
    SUBSTRING("substring", 0),
    TRIM("trim", 0),
    LENGTH("length", 0),

    ADD("+", 4),
    SUBTRACT("-", 4),
    MULTIPLY("*", 3),
    DIVIDE("/", 3),
    MOD("mod", 3),

    NULLIF("nullif", 0),
    ISNULL("isnull", 0),
    IN("in", 0),
    BETWEEN("between", 8),

    //aggregate function

    MIN("min", 0),
    MAX("max", 0),
    COUNT("count", 0),
    AVG("avg", 0),
    SUM("sum", 0);


    private final String sign;
    private final int precedence;

    Operator(String sign, int priority) {
        this.sign = sign;
        this.precedence = priority;
    }


    @Override
    public String toString() {
        return sign;
    }

}
