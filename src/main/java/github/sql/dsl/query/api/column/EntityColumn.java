package github.sql.dsl.query.api.column;

import github.sql.dsl.query.api.Entity;

import java.util.Date;

@FunctionalInterface
public interface EntityColumn<T, R extends Entity> extends Column<T, R> {

    static <T, R extends Entity> EntityColumn<T, R> of(EntityColumn<T, R> column) {
        return column;
    }

    default <V extends Entity> EntityColumn<T, V> to(EntityColumn<R, V> column) {
        throw new UnsupportedOperationException();
    }

    default <V extends Number> NumberColumn<T, V> to(NumberColumn<R, V> column) {
        throw new UnsupportedOperationException();
    }

    default <V extends Date> DateColumn<T, V> to(DateColumn<R, V> column) {
        throw new UnsupportedOperationException();
    }

    default StringColumn<T> to(StringColumn<R> column) {
        throw new UnsupportedOperationException();
    }


    default BooleanColumn<T> to(BooleanColumn<R> column) {
        throw new UnsupportedOperationException();
    }

    default <V> Column<T, V> to(Column<R, V> column) {
        throw new UnsupportedOperationException();
    }


}
