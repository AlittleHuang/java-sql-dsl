package github.sql.dsl.criteria.query.support.meta;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Map;

public interface ProjectionProxyInstance {

    Method TO_STRING_METHOD = getToStringMethod();
    Method EQUALS_METHOD = getEqualsMethod();
    Method GET_RESULT_MAP_METHOD = getResultMapMethod();
    Method GET_PROJECTION_CLASS_METHOD = getProjectionClassMethod();


    @SneakyThrows
    @NotNull
    static Method getToStringMethod() {
        return Object.class.getMethod("toString");
    }


    @SneakyThrows
    @NotNull
    static Method getEqualsMethod() {
        return Object.class.getMethod("equals", Object.class);
    }


    @SneakyThrows
    @NotNull
    static Method getResultMapMethod() {
        return ProjectionProxyInstance.class.getMethod("$getResultMap$");
    }

    @SneakyThrows
    @NotNull
    static Method getProjectionClassMethod() {
        return ProjectionProxyInstance.class.getMethod("$getProjectionClass$");
    }

    Map<Method, Object> $getResultMap$();

    Class<?> $getProjectionClass$();

}
