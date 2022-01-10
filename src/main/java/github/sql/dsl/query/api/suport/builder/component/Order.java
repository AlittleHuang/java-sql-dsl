package github.sql.dsl.query.api.suport.builder.component;

import github.sql.dsl.query.api.expression.Expression;
import lombok.Getter;

@Getter
public class Order {

    private final Expression<?> expression;
    private final boolean desc;


    public Order(Expression<?> expression, boolean desc) {
        this.expression = expression;
        this.desc = desc;
    }


}
