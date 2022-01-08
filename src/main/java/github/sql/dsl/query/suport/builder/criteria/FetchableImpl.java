package github.sql.dsl.query.suport.builder.criteria;

import github.sql.dsl.query.api.builder.Fetchable;
import github.sql.dsl.query.api.expression.PathExpression;
import github.sql.dsl.query.api.expression.path.AttributePath;
import github.sql.dsl.query.api.expression.path.EntityPath;
import github.sql.dsl.query.api.expression.path.attribute.EntityAttribute;
import github.sql.dsl.query.suport.builder.component.ConstantList;
import github.sql.dsl.util.Array;
import lombok.Getter;

import java.util.function.Function;

@Getter
public class FetchableImpl<T, NEXT> implements Fetchable<T, NEXT> {

    private final Array<PathExpression<?>> values;
    private final Function<Array<PathExpression<?>>, NEXT> mapper;

    public FetchableImpl(Array<PathExpression<?>> values,
                         Function<Array<PathExpression<?>>, NEXT> mapper) {
        this.values = values;
        this.mapper = mapper;
    }

    @Override
    public NEXT fetch(EntityAttribute<T, ?> attribute) {
        EntityPath<T, ?> exchange = AttributePath.exchange(attribute);
        Array<PathExpression<?>> then = this.values == null
                ? new ConstantList<>(exchange)
                : this.values.concat(exchange);
        return mapper.apply(then);
    }

}
