package github.sql.dsl.query.api.builder;

import github.sql.dsl.query.api.expression.path.bridge.ComparableAttributeBridge;
import github.sql.dsl.query.api.expression.path.bridge.NumberAttributeBridge;
import github.sql.dsl.query.api.expression.path.bridge.StringAttributeBridge;

import java.util.Date;

public interface Sortable<T, NEXT> {

    <U extends Number> Sort<NEXT> orderBy(NumberAttributeBridge<T, U> column);

    <U extends Date> Sort<NEXT> orderBy(ComparableAttributeBridge<T, U> column);

    Sort<NEXT> orderBy(StringAttributeBridge<T> column);


    interface Sort<T> {


        T asc();

        T desc();


    }
}
