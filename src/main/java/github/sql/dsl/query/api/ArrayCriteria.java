package github.sql.dsl.query.api;

import github.sql.dsl.query.api.column.Column;

import java.util.List;

public interface ArrayCriteria<T> extends ArrayResults {

    ArrayCriteria<T> groupBy(Column<T, ?> column);

    ArrayCriteria<T> groupBy(List<Column<T, ?>> columns);

    Selections<T> select(Column<T, ?> column);

    Selections<T> select(List<Column<T, ?>> columns);

}
