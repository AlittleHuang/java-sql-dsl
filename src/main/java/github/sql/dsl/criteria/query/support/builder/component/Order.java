package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.expression.SqlExpression;
import lombok.Getter;

@Getter
public class Order {

    private final SqlExpression<?> expression;
    private final boolean desc;


    public Order(SqlExpression<?> expression, boolean desc) {
        this.expression = expression;
        this.desc = desc;
    }


}
