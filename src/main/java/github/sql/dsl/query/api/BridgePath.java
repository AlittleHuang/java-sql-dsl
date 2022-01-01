package github.sql.dsl.query.api;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Objects;

public class BridgePath<T, R>
        extends PathExpression<T>
        implements AttributeBridge<T, R> {

    public BridgePath(String... path) {
        super(path);
    }

    public static Expression<?> asExpression(AttributeBridge<?, ?> attributeBridge) {
        return exchange(attributeBridge);
    }

    public static <T, R extends Entity> EntityBridge<T, R> exchange(EntityAttributeBridge<T, R> attributeBridge) {
        if (attributeBridge instanceof EntityBridge) {
            return (EntityBridge<T, R>) attributeBridge;
        }
        return new EntityBridge<>(getAttributeName(attributeBridge));
    }

    public static <T, R> BridgePath<T, R> exchange(AttributeBridge<T, R> attributeBridge) {
        if (attributeBridge instanceof BridgePath) {
            return (BridgePath<T, R>) attributeBridge;
        }
        return new BridgePath<>(getAttributeName(attributeBridge));
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

    private static String getAttributeName(AttributeBridge<?, ?> attributeBridge) {
        return toAttrName(getLambdaMethodName(attributeBridge));
    }

    @Override
    public R bridge(T t) {
        throw new UnsupportedOperationException();
    }


    public <V extends Entity> EntityBridge<T, V> get(EntityAttributeBridge<R, V> attribute) {
        return new EntityBridge<>(pathTo(attribute));
    }

    public <V extends Number> NumberBridge<T, V> get(NumberAttributeBridge<R, V> attribute) {
        return new NumberBridge<>(pathTo(attribute));
    }

    public <V extends Date> ComparableBridge<T, V> get(ComparableAttributeBridge<R, V> attribute) {
        return new ComparableBridge<>(pathTo(attribute));
    }

    public StringBridge<T> get(StringAttributeBridge<R> attribute) {
        return new StringBridge<>(pathTo(attribute));
    }

    public BooleanBridge<T> get(BooleanAttributeBridge<R> attribute) {
        return new BooleanBridge<>(pathTo(attribute));
    }

    public <V> BridgePath<T, V> get(AttributeBridge<R, V> attribute) {
        return new BridgePath<>(pathTo(attribute));
    }

    public <V> BridgePath<T, V> to(AttributeBridge<?, ?> attribute) {
        return new BridgePath<>(pathTo(attribute));
    }

    private String[] pathTo(AttributeBridge<?, ?> attribute) {
        String[] path = new String[length + 1];
        this.arraycopy(0, path, 0, length);
        path[this.length] = getAttributeName(attribute);
        return path;
    }

    public static class BooleanBridge<T>
            extends BridgePath<T, Boolean>
            implements BooleanAttributeBridge<T> {
        public BooleanBridge(String... path) {
            super(path);
        }
    }

    public static class StringBridge<T>
            extends BridgePath<T, String>
            implements StringAttributeBridge<T> {
        public StringBridge(String... path) {
            super(path);
        }
    }

    public static class ComparableBridge<T, R extends Comparable<?>>
            extends BridgePath<T, R>
            implements ComparableAttributeBridge<T, R> {
        public ComparableBridge(String... path) {
            super(path);
        }

    }

    public static class EntityBridge<T, R extends Entity>
            extends BridgePath<T, R>
            implements EntityAttributeBridge<T, R> {
        public EntityBridge(String... path) {
            super(path);
        }

    }

    public static class NumberBridge<T, R extends Number>
            extends BridgePath<T, R>
            implements NumberAttributeBridge<T, R> {
        public NumberBridge(String... path) {
            super(path);
        }

    }
}
