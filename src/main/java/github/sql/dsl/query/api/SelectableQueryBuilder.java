package github.sql.dsl.query.api;

import java.util.List;

public interface SelectableQueryBuilder<T> extends ObjectsTypeQuery {

    SelectableQueryBuilder<T> select(AttributeBridge<T, ?> selection);

    SelectableQueryBuilder<T> select(List<AttributeBridge<T, ?>> selections);

}
