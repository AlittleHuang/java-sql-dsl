package github.sql.dsl.query.api.expression.path.bridge;

import github.sql.dsl.query.api.expression.path.*;

import java.util.Date;

@FunctionalInterface
public interface EntityAttributeBridge<T, R extends Entity> extends AttributeBridge<T, R> {

    static <T, R extends Entity> EntityAttributeBridge<T, R> of(EntityAttributeBridge<T, R> attribute) {
        return BridgePath.exchange(attribute);
    }

    default <V extends Entity> EntityPath<T, V> map(EntityAttributeBridge<R, V> attribute) {
        throw new UnsupportedOperationException();
    }

    default <V extends Number> NumberPath<T, V> map(NumberAttributeBridge<R, V> attribute) {
        throw new UnsupportedOperationException();
    }

    default <V extends Date> ComparablePath<T, V> map(ComparableAttributeBridge<R, V> attribute) {
        throw new UnsupportedOperationException();
    }

    default StringPath<T> map(StringAttributeBridge<R> attribute) {
        throw new UnsupportedOperationException();
    }

    default BooleanPath<T> map(BooleanAttributeBridge<R> attribute) {
        throw new UnsupportedOperationException();
    }

    default <V> AttributeBridge<T, V> map(AttributeBridge<R, V> attribute) {
        throw new UnsupportedOperationException();
    }


}
