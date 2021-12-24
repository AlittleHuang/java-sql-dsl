package github.sql.dsl.query.api;

import github.sql.dsl.query.api.column.EntityColumn;

import java.util.List;

public interface Selections<T> {

    SelectionBuilder<T> select(EntityColumn<T, ?> selections);

    SelectionBuilder<T> select(List<EntityColumn<T, ?>> selections);

}
