package github.sql.dsl.query.api.condition;

import github.sql.dsl.query.api.Entity;
import github.sql.dsl.query.api.WhereClauses;
import github.sql.dsl.query.api.column.DateColumn;
import github.sql.dsl.query.api.column.EntityColumn;
import github.sql.dsl.query.api.column.NumberColumn;
import github.sql.dsl.query.api.column.StringColumn;

import java.util.Date;

public interface EntityConditionBuilder<T, U> {

    <V extends Entity> EntityConditionBuilder<T, V> to(EntityColumn<U, V> column);

    <V extends Number> NumberConditionBuilder<T, V> to(NumberColumn<U, V> column);

    <V extends Date> ComparableConditionBuilder<T, V> to(DateColumn<U, V> column);

    StringConditionBuilder<T> to(StringColumn<U> column);

    WhereClauses<T> isNull();

}
