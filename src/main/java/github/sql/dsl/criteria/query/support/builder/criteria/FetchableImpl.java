package github.sql.dsl.criteria.query.support.builder.criteria;

import github.sql.dsl.criteria.query.builder.Fetchable;
import github.sql.dsl.criteria.query.expression.PathExpression;
import github.sql.dsl.criteria.query.expression.path.AttributePath;
import github.sql.dsl.criteria.query.expression.path.EntityPath;
import github.sql.dsl.criteria.query.expression.path.attribute.EntityAttribute;
import github.sql.dsl.criteria.query.support.builder.component.ConstantList;
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
