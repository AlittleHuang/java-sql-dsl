package github.sql.dsl.query.api.column;

import github.sql.dsl.query.api.Entity;

import java.util.Date;

@SuppressWarnings("unchecked")
@FunctionalInterface
public interface EntityColumn<T, R extends Entity> extends Column<T, R> {

    static <T, R extends Entity> EntityColumn<T, R> of(EntityColumn<T, R> column) {
        return column;
    }

    default <V extends Entity> EntityColumn<T, V> to(EntityColumn<R, V> column) {
        return ColumnExpression.exchange(column).to(column);
    }

    default <V extends Number> NumberColumn<T, V> to(NumberColumn<R, V> column) {
        return ColumnExpression.exchange(column).to(column);
    }

    default <V extends Date> DateColumn<T, V> to(DateColumn<R, V> column) {
        return ColumnExpression.exchange(column).to(column);
    }

    default StringColumn<T> to(StringColumn<R> column) {
        return ColumnExpression.exchange(column).to(column);
    }


    default BooleanColumn<T> to(BooleanColumn<R> column) {
        return ColumnExpression.exchange(column).to(column);
    }

    default <V> Column<T, V> to(Column<R, V> column) {
        return ColumnExpression.exchange(column).to(column);
    }


}
