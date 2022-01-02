package github.sql.dsl.query.api.expression.path;

import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.PathExpression;
import github.sql.dsl.query.api.expression.path.bridge.*;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Objects;

public class BridgePath<T, R>
        extends PathExpression<R>
        implements AttributeBridge<T, R> {

    public BridgePath(String... path) {
        super(path);
    }

    public static Expression<?> asExpression(AttributeBridge<?, ?> attribute) {
        return exchange(attribute);
    }

    public static <T, R extends Entity> EntityPath<T, R> exchange(EntityAttributeBridge<T, R> attribute) {
        if (attribute instanceof EntityPath) {
            return (EntityPath<T, R>) attribute;
        }
        return new EntityPath<>(getAttributeName(attribute));
    }

    public static <T, R> BridgePath<T, R> exchange(AttributeBridge<T, R> attribute) {
        if (attribute instanceof BridgePath) {
            return (BridgePath<T, R>) attribute;
        }
        return new BridgePath<>(getAttributeName(attribute));
    }

    public static String toAttrName(String getterName) {
        StringBuilder builder = null;
        if (getterName != null) {
            if (getterName.length() > 3 && getterName.startsWith("get")) {
                builder = new StringBuilder(getterName.substring(3));
            } else if (getterName.length() > 2 && getterName.startsWith("is")) {
                builder = new StringBuilder(getterName.substring(2));
            }
        }
        Objects.requireNonNull(builder, "the function is not getters");
        if (builder.length() == 1) {
            return builder.toString().toLowerCase();
        }
        if (Character.isUpperCase(builder.charAt(1))) {
            return builder.toString();
        }
        builder.setCharAt(0, Character.toLowerCase(builder.charAt(0)));
        return builder.toString();
    }

    public static String getLambdaMethodName(Serializable lambda) {
        try {
            Method method = lambda.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(lambda);
            return serializedLambda.getImplMethodName();
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static String getAttributeName(AttributeBridge<?, ?> attribute) {
        return toAttrName(getLambdaMethodName(attribute));
    }

    @Override
    public R bridge(T t) {
        throw new UnsupportedOperationException();
    }


    public <V extends Entity> EntityPath<T, V> map(EntityAttributeBridge<R, V> attribute) {
        return new EntityPath<>(pathTo(attribute));
    }

    public <V extends Number> NumberPath<T, V> map(NumberAttributeBridge<R, V> attribute) {
        return new NumberPath<>(pathTo(attribute));
    }

    public <V extends Date> ComparablePath<T, V> map(ComparableAttributeBridge<R, V> attribute) {
        return new ComparablePath<>(pathTo(attribute));
    }

    public StringPath<T> map(StringAttributeBridge<R> attribute) {
        return new StringPath<>(pathTo(attribute));
    }

    public BooleanPath<T> map(BooleanAttributeBridge<R> attribute) {
        return new BooleanPath<>(pathTo(attribute));
    }

    public <V> BridgePath<T, V> map(AttributeBridge<R, V> attribute) {
        return new BridgePath<>(pathTo(attribute));
    }

    public <V> BridgePath<T, V> mapTo(AttributeBridge<?, ?> attribute) {
        return new BridgePath<>(pathTo(attribute));
    }

    private String[] pathTo(AttributeBridge<?, ?> attribute) {
        String[] path = new String[length + 1];
        this.arraycopy(0, path, 0, length);
        path[this.length] = getAttributeName(attribute);
        return path;
    }

}
