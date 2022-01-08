package github.sql.dsl.query.api.builder.combination;

import github.sql.dsl.query.api.expression.BooleanExpression;
import github.sql.dsl.query.api.expression.path.Entity;
import github.sql.dsl.query.api.expression.path.PathBuilder;
import github.sql.dsl.query.api.expression.path.attribute.*;

import java.util.Date;
import java.util.function.Function;

public interface Whereable<T, NEXT> {

    <U extends Entity> PathBuilder<T, U, NEXT> where(EntityAttribute<T, U> column);

    <U> ExpressionBuilder<T, U, NEXT> where(Attribute<T, U> attribute);

    <U extends Number> NumberExpressionBuilder<T, U, NEXT> where(NumberAttribute<T, U> column);

    <U extends Date> ComparableExpressionBuilder<T, U, NEXT> where(ComparableAttribute<T, U> column);

    StringExpressionBuilder<T, NEXT> where(StringAttribute<T> column);

    <U extends Entity> PathBuilder<T, U, NEXT> whereNot(EntityAttribute<T, U> column);

    <U> ExpressionBuilder<T, U, NEXT> whereNot(Attribute<T, U> attribute);

    <U extends Number> NumberExpressionBuilder<T, U, NEXT> whereNot(NumberAttribute<T, U> column);

    <U extends Date> ComparableExpressionBuilder<T, U, NEXT> whereNot(ComparableAttribute<T, U> column);

    StringExpressionBuilder<T, NEXT> whereNot(StringAttribute<T> column);

    NEXT Where(Function<SubPredicateHeaderCombinable<T, SubPredicateCombinable<T, NEXT>>, BooleanExpression> builder);


}
