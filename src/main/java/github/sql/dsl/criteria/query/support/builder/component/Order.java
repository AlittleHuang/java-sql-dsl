package github.sql.dsl.criteria.query.support.builder.component;

import github.sql.dsl.criteria.query.expression.Expression;
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
