package github.sql.dsl.query.api;

@FunctionalInterface
public interface ComparableAttributeBridge<T, U extends Comparable<?>> extends AttributeBridge<T, U> {

}
