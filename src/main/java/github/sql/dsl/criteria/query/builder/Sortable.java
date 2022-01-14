package github.sql.dsl.criteria.query.builder;

import github.sql.dsl.criteria.query.expression.path.attribute.ComparableAttribute;
import github.sql.dsl.criteria.query.expression.path.attribute.NumberAttribute;
import github.sql.dsl.criteria.query.expression.path.attribute.StringAttribute;

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