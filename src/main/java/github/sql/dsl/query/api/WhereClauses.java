package github.sql.dsl.query.api;

import github.sql.dsl.query.api.builder.*;
import github.sql.dsl.query.api.column.*;

import java.util.Date;

public interface WhereClauses<T> extends Results<T>, Criteria<T> {

    <U extends Entity> EntityConditionBuilder<T, U> and(EntityColumn<T, U> column);

    <U extends Entity> EntityConditionBuilder<T, U> or(EntityColumn<T, U> column);

    <U extends Entity> EntityConditionBuilder<T, U> andNot(EntityColumn<T, U> column);

    <U extends Entity> EntityConditionBuilder<T, U> orNot(EntityColumn<T, U> column);

    <U> ConditionBuilder<T, U> and(Column<T, U> column);

    <U> ConditionBuilder<T, U> or(Column<T, U> column);

    <U> ConditionBuilder<T, U> andNot(Column<T, U> column);

    <U> ConditionBuilder<T, U> orNot(Column<T, U> column);

    <U extends Number> NumberConditionBuilder<T, U> and(NumberColumn<T, U> column);

    <U extends Number> NumberConditionBuilder<T, U> or(NumberColumn<T, U> column);

    <U extends Number> NumberConditionBuilder<T, U> andNot(NumberColumn<T, U> column);

    <U extends Number> NumberConditionBuilder<T, U> orNot(NumberColumn<T, U> column);

    <U extends Date> ComparableConditionBuilder<T, U> and(DateColumn<T, U> column);

    <U extends Date> ComparableConditionBuilder<T, U> or(DateColumn<T, U> column);

    <U extends Date> ComparableConditionBuilder<T, U> andNot(DateColumn<T, U> column);

    <U extends Date> ComparableConditionBuilder<T, U> orNot(DateColumn<T, U> column);

    StringConditionBuilder<T> and(StringColumn<T> column);

    StringConditionBuilder<T> or(StringColumn<T> column);

    StringConditionBuilder<T> andNot(StringColumn<T> column);

    StringConditionBuilder<T> orNot(StringColumn<T> column);

}
