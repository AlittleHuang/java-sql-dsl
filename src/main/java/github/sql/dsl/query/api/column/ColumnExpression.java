package github.sql.dsl.query.api.column;

import github.sql.dsl.query.suport.common.expression.Expression;
import github.sql.dsl.query.suport.common.model.AbstractExpression;
import lombok.Getter;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
@SuppressWarnings("rawtypes")
public final class ColumnExpression extends AbstractExpression implements EntityColumn, BooleanColumn,
        StringColumn, DateColumn, NumberColumn {

    private final List<String> path;

    private ColumnExpression(String... path) {
        this.path = Arrays.asList(path);
    }

    public static ColumnExpression exchange(Column<?, ?> column) {
        if (column instanceof ColumnExpression) {
            return (ColumnExpression) column;
        }
        return new ColumnExpression(getAttributeName(column));
    }

    public static <T> Expression<T> asExpression(Column<?, T> column) {
        //noinspection unchecked
        return exchange(column);
    }

    @Override
    public ColumnExpression to(EntityColumn column) {
        return to((Column) column);
    }

    @Override
    public ColumnExpression to(NumberColumn column) {
        return to((Column) column);
    }

    @Override
    public ColumnExpression to(DateColumn column) {
        return to((Column) column);
    }

    @Override
    public ColumnExpression to(StringColumn column) {
        return to((Column) column);
    }

    @Override
    public ColumnExpression to(BooleanColumn column) {
        return to((Column) column);
    }

    @Override
    public ColumnExpression to(Column column) {
        String[] path = this.path.toArray(new String[this.path.size() + 1]);
        path[this.path.size()] = getAttributeName(column);
        return new ColumnExpression(path);
    }

    @Override
    public Object apply(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "" + path;
    }

    private static String getAttributeName(Column column) {
        return toAttrName(getLambdaMethodName(column));
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

}
