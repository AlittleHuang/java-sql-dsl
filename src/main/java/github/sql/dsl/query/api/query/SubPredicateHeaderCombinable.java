package github.sql.dsl.query.api.query;

import github.sql.dsl.query.api.expression.ComparableExpressionBuilder;
import github.sql.dsl.query.api.expression.ExpressionBuilder;
import github.sql.dsl.query.api.expression.NumberExpressionBuilder;
import github.sql.dsl.query.api.expression.StringExpressionBuilder;
import github.sql.dsl.query.api.expression.path.Entity;
import github.sql.dsl.query.api.expression.path.PathBuilder;
import github.sql.dsl.query.api.expression.path.bridge.*;

import java.util.Date;

public interface SubPredicateHeaderCombinable<T, NEXT> {

    <U extends Entity> PathBuilder<T, U, NEXT> get(EntityAttribute<T, U> column);

    <U> ExpressionBuilder<T, U, NEXT> get(Attribute<T, U> attribute);

    <U extends Number> NumberExpressionBuilder<T, U, NEXT> get(NumberAttribute<T, U> column);

    <U extends Date> ComparableExpressionBuilder<T, U, NEXT> get(ComparableAttribute<T, U> column);

    StringExpressionBuilder<T, NEXT> get(StringAttribute<T> column);

    <U extends Entity> PathBuilder<T, U, NEXT> not(EntityAttribute<T, U> column);

    <U> ExpressionBuilder<T, U, NEXT> not(Attribute<T, U> attribute);

    <U extends Number> NumberExpressionBuilder<T, U, NEXT> not(NumberAttribute<T, U> column);

    <U extends Date> ComparableExpressionBuilder<T, U, NEXT> not(ComparableAttribute<T, U> column);

    StringExpressionBuilder<T, NEXT> not(StringAttribute<T> column);

}
