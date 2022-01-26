package github.sql.dsl.criteria.query.support;

import github.sql.dsl.criteria.query.builder.TypeResultQuery;
import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.path.AttributePath;
import github.sql.dsl.criteria.query.support.builder.component.ConstantArray;
import github.sql.dsl.criteria.query.support.builder.query.CriteriaQueryImpl;
import github.sql.dsl.criteria.query.support.meta.ProjectionAttribute;
import github.sql.dsl.criteria.query.support.meta.ProjectionInformation;
import github.sql.dsl.criteria.query.support.meta.ProjectionProxyInstance;
import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface TypeQueryFactory {

    <T> TypeResultQuery<T> getEntityResultQuery(CriteriaQuery criteriaQuery, Class<T> type);

    default <T, R> TypeResultQuery<R> getProjectionQuery(CriteriaQuery criteriaQuery,
                                                         Class<T> type,
                                                         Class<R> projectionType) {
        return new TypeResultQuery<R>() {
            @Override
            public int count() {
                return getEntityResultQuery(criteriaQuery, type).count();
            }

            @Override
            public List<R> getResultList(int offset, int maxResult) {
                ProjectionInformation info = ProjectionInformation.get(type, projectionType);
                ArrayList<String> paths = new ArrayList<>();
                for (ProjectionAttribute attribute : info) {
                    paths.add(attribute.getFieldName());
                }
                Expression<?>[] selections = paths.stream()
                        .map(AttributePath::new)
                        .toArray(Expression[]::new);
                ConstantArray<Expression<?>> array = new ConstantArray<>(selections);
                CriteriaQueryImpl cq = CriteriaQueryImpl.from(criteriaQuery)
                        .updateSelection(array);
                List<Object[]> objects = getObjectsTypeQuery(cq, type)
                        .getResultList(offset, maxResult);
                return objects.stream()
                        .map(os -> mapToRejection(info, paths, os, projectionType))
                        .collect(Collectors.toList());
            }

            @SneakyThrows
            private R mapToRejection(ProjectionInformation info, ArrayList<String> paths, Object[] os, Class<R> projectionType) {
                ClassLoader classLoader = projectionType.getClassLoader();
                Class<?>[] interfaces = {projectionType, ProjectionProxyInstance.class};

                if (projectionType.isInterface()) {
                    Map<Method, Object> map = new HashMap<>();
                    int i = 0;
                    for (ProjectionAttribute attribute : info) {
                        Object value = os[i++];
                        map.put(attribute.getGetter(), value);
                    }
                    //noinspection unchecked
                    return (R) Proxy.newProxyInstance(classLoader, interfaces, (proxy, method, args) -> {
                        if (map.containsKey(method)) {
                            return map.get(method);
                        }
                        if (ProjectionProxyInstance.TO_STRING_METHOD.equals(method)) {
                            Map<String, Object> stringMap = new HashMap<>();
                            for (ProjectionAttribute attribute : info) {
                                stringMap.put(attribute.getFieldName(), map.get(attribute.getGetter()));
                            }
                            return projectionType.getSimpleName() + stringMap;
                        }

                        if (ProjectionProxyInstance.GET_RESULT_MAP_METHOD.equals(method)) {
                            return map;
                        }

                        if (ProjectionProxyInstance.GET_PROJECTION_CLASS_METHOD.equals(method)) {
                            return projectionType;
                        }

                        if (ProjectionProxyInstance.EQUALS_METHOD.equals(method)) {
                            if (args[0] instanceof ProjectionProxyInstance) {
                                ProjectionProxyInstance instance = (ProjectionProxyInstance) args[0];
                                if (instance.$getProjectionClass$() == projectionType) {
                                    return map.equals(instance.$getResultMap$());
                                }
                            }
                            return false;
                        }
                        if (method.getDeclaringClass() == Object.class) {
                            return method.invoke(map, args);
                        }
                        throw new AbstractMethodError(method.toString());
                    });
                } else {
                    R result = projectionType.getConstructor().newInstance();
                    for (int j = 0; j < os.length; j++) {
                        String name = paths.get(j);
                        ProjectionAttribute attribute = info.get(name);
                        Object value = os[j];
                        attribute.setValue(result, value);
                    }
                    return result;
                }
            }

            @Override
            public boolean exist(int offset) {
                return getEntityResultQuery(criteriaQuery, type).exist(offset);
            }
        };
    }

    TypeResultQuery<Object[]> getObjectsTypeQuery(CriteriaQuery criteriaQuery, Class<?> type);


}
