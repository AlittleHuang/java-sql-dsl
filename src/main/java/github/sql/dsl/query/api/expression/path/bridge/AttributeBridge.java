package github.sql.dsl.query.api.expression.path.bridge;

import github.sql.dsl.query.api.expression.path.BridgePath;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;

@FunctionalInterface
public interface AttributeBridge<T, R> extends Serializable {

    R bridge(T t);

    static <T, R> @NotNull AttributeBridge<T, R> of(AttributeBridge<T, R> attribute) {
        return BridgePath.exchange(attribute);
    }


    static <T, R extends Number> NumberAttributeBridge<T, R> of(NumberAttributeBridge<T, R> attribute) {
        throw new UnsupportedOperationException();
    }


    static <T> StringAttributeBridge<T> of(StringAttributeBridge<T> attribute) {
        throw new UnsupportedOperationException();
    }


    static <T, R extends Date> ComparableAttributeBridge<T, R> of(ComparableAttributeBridge<T, R> attribute) {
        throw new UnsupportedOperationException();
    }


    static <T, R extends Number> BooleanAttributeBridge<T> of(BooleanAttributeBridge<T> attribute) {
        throw new UnsupportedOperationException();
    }


}
