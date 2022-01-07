package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.expression.path.bridge.ComparableAttribute;
import github.sql.dsl.query.api.expression.path.bridge.NumberAttribute;
import github.sql.dsl.query.api.expression.path.bridge.StringAttribute;

import java.util.Date;

public interface Sortable<T, NEXT> {

    <U extends Number> Sort<NEXT> orderBy(NumberAttribute<T, U> column);

    <U extends Date> Sort<NEXT> orderBy(ComparableAttribute<T, U> column);

    Sort<NEXT> orderBy(StringAttribute<T> column);


    interface Sort<T> {


        T asc();

        T desc();


    }
}
