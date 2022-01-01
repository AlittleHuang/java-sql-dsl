package github.sql.dsl.query.api;

import java.util.Date;

@FunctionalInterface
public interface EntityAttributeBridge<T, R extends Entity> extends AttributeBridge<T, R> {

    static <T, R extends Entity> EntityAttributeBridge<T, R> of(EntityAttributeBridge<T, R> attribute) {
        return BridgePath.exchange(attribute);
    }

    default <V extends Entity> EntityAttributeBridge<T, V> get(EntityAttributeBridge<R, V> attribute) {
        throw new UnsupportedOperationException();
    }

    default <V extends Number> NumberAttributeBridge<T, V> get(NumberAttributeBridge<R, V> attribute) {
        throw new UnsupportedOperationException();
    }

    default <V extends Date> ComparableAttributeBridge<T, V> get(ComparableAttributeBridge<R, V> attribute) {
        throw new UnsupportedOperationException();
    }

    default StringAttributeBridge<T> get(StringAttributeBridge<R> attribute) {
        throw new UnsupportedOperationException();
    }

    default BooleanAttributeBridge<T> get(BooleanAttributeBridge<R> attribute) {
        throw new UnsupportedOperationException();
    }

    default <V> AttributeBridge<T, V> get(AttributeBridge<R, V> attributeBridge) {
        throw new UnsupportedOperationException();
    }


}
