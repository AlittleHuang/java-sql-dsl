package github.sql.dsl.query.api.column;

import github.sql.dsl.query.suport.common.model.ColumnExpression;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

@FunctionalInterface
public interface Column<T, R> extends Function<T, R>, Serializable {

    static <T, R> Column<T, R> of(Column<T, R> column) {
        //noinspection unchecked
        return ColumnExpression.exchange(column);
    }


    static <T, R extends Number> NumberColumn<T, R> of(NumberColumn<T, R> column) {
        //noinspection unchecked
        return ColumnExpression.exchange(column);
    }


    static <T> StringColumn<T> of(StringColumn<T> column) {
        //noinspection unchecked
        return ColumnExpression.exchange(column);
    }


    static <T, R extends Date> DateColumn<T, R> of(DateColumn<T, R> column) {
        //noinspection unchecked
        return ColumnExpression.exchange(column);
    }


    static <T, R extends Number> BooleanColumn<T> of(BooleanColumn<T> column) {
        //noinspection unchecked
        return ColumnExpression.exchange(column);
    }



}
