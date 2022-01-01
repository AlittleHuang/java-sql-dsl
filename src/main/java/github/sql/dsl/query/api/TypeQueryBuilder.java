package github.sql.dsl.query.api;

import java.util.Date;
import java.util.List;

public interface TypeQueryBuilder<T> extends TypeQuery<T> {

    <U extends Number> Sortable<TypeQueryBuilder<T>> orderBy(NumberAttributeBridge<T, U> column);

    <U extends Date> Sortable<TypeQueryBuilder<T>> orderBy(ComparableAttributeBridge<T, U> column);

    Sortable<TypeQueryBuilder<T>> orderBy(StringAttributeBridge<T> column);

    ObjectsTypeQueryBuilder<T> groupBy(AttributeBridge<T, ?> selections);

    ObjectsTypeQueryBuilder<T> groupBy(List<AttributeBridge<T, ?>> attributeBridges);

    SelectableQueryBuilder<T> select(AttributeBridge<T, ?> selection);

    SelectableQueryBuilder<T> select(List<AttributeBridge<T, ?>> attributeBridges);

    FetchBuilder<T> fetch(EntityAttributeBridge<T, ?> column);

}
