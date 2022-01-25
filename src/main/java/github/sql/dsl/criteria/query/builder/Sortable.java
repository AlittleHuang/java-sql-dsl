package github.sql.dsl.criteria.query.builder;

import github.sql.dsl.criteria.query.expression.path.attribute.ComparableAttribute;
import github.sql.dsl.criteria.query.expression.path.attribute.NumberAttribute;
import github.sql.dsl.criteria.query.expression.path.attribute.StringAttribute;

public interface Sortable<T, NEXT> {

    <U extends Number & Comparable<?>> Sort<NEXT> orderBy(NumberAttribute<T, U> column);

    <U extends Comparable<?>> Sort<NEXT> orderBy(ComparableAttribute<T, U> column);

    Sort<NEXT> orderBy(StringAttribute<T> column);


    interface Sort<T> {


        T asc();

        T desc();


    }
}
