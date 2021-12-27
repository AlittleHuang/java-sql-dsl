package github.sql.dsl.query.api;

import github.sql.dsl.query.api.column.Column;

import java.util.List;

public interface Selections<T> extends ArrayResults {

    Selections<T> select(Column<T, ?> selection);

    Selections<T> select(List<Column<T, ?>> selections);

}
