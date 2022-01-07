package github.sql.dsl.query.api.expression.path;

import github.sql.dsl.query.api.expression.ComparableExpressionBuilder;
import github.sql.dsl.query.api.expression.ExpressionBuilder;
import github.sql.dsl.query.api.expression.NumberExpressionBuilder;
import github.sql.dsl.query.api.expression.StringExpressionBuilder;
import github.sql.dsl.query.api.expression.path.bridge.*;

import java.util.Date;

public interface PathBuilder<T, U, NEXT> {

    <R extends Entity> PathBuilder<T, R, NEXT> map(EntityAttribute<U, R> column);

    <R extends Number> NumberExpressionBuilder<T, R, NEXT> map(NumberAttribute<U, R> column);

    <R extends Date> ComparableExpressionBuilder<T, R, NEXT> map(ComparableAttribute<U, R> column);

    <R extends Date> ExpressionBuilder<T, R, NEXT> map(Attribute<U, R> attribute);

    StringExpressionBuilder<T, NEXT> map(StringAttribute<U> column);


}
