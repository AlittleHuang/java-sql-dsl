package github.sql.dsl.query.api;

import java.util.List;

public interface ObjectsTypeQueryBuilder<T> {

    ObjectsTypeQueryBuilder<T> groupBy(AttributeBridge<T, ?> attributeBridge);

    ObjectsTypeQueryBuilder<T> groupBy(List<AttributeBridge<T, ?>> attributeBridges);

    SelectableQueryBuilder<T> select(AttributeBridge<T, ?> attributeBridge);

    SelectableQueryBuilder<T> select(List<AttributeBridge<T, ?>> attributeBridges);

}
