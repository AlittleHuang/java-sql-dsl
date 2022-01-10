package github.sql.dsl.query.api.expression.path.attribute;

import github.sql.dsl.query.api.expression.path.AttributePath;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;

@FunctionalInterface
public interface Attribute<T, R> extends Serializable {

    R map(T t);

    static <T, R> @NotNull Attribute<T, R> of(Attribute<T, R> attribute) {
        return AttributePath.exchange(attribute);
    }


    static <T, R extends Number> NumberAttribute<T, R> of(NumberAttribute<T, R> attribute) {
        return AttributePath.fromNumberAttributeBridge(attribute);
    }


    static <T> StringAttribute<T> of(StringAttribute<T> attribute) {
        return AttributePath.fromStringAttributeBridge(attribute);
    }


    static <T, R extends Date> ComparableAttribute<T, R> of(ComparableAttribute<T, R> attribute) {
        return AttributePath.fromComparableAttributeBridge(attribute);
    }


    static <T> BooleanAttribute<T> of(BooleanAttribute<T> attribute) {
        return AttributePath.fromBooleanAttributeBridge(attribute);
    }


}
