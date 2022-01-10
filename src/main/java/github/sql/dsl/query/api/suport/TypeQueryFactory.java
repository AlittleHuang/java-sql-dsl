package github.sql.dsl.query.api.suport;

import github.sql.dsl.query.api.builder.ArrayResultQuery;
import github.sql.dsl.query.api.builder.EntityResultQuery;
import github.sql.dsl.query.api.builder.ProjectionResultQuery;
import github.sql.dsl.query.api.expression.path.AttributePath;
import github.sql.dsl.query.api.suport.builder.component.ConstantList;
import github.sql.dsl.query.api.suport.builder.component.Selection;
import github.sql.dsl.query.api.suport.builder.query.CriteriaQueryImpl;
import github.sql.dsl.query.api.suport.meta.ProjectionAttribute;
import github.sql.dsl.query.api.suport.meta.ProjectionInformation;
import github.sql.dsl.query.api.suport.meta.ProjectionProxyInstance;
import github.sql.dsl.util.JacksonMapper;
import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface TypeQueryFactory {

    <T> EntityResultQuery<T> getTypeQuery(CriteriaQuery criteriaQuery, Class<T> type);

    default <T, R> ProjectionResultQuery<R> getProjectionQuery(CriteriaQuery criteriaQuery,
                                                               Class<T> type,
                                                               Class<R> projectionType) {
        return new ProjectionResultQuery<R>() {
            @Override
            public int count() {
                return getTypeQuery(criteriaQuery, type).count();
            }

            @Override
            public List<R> getResultList(int offset, int maxResult) {
                ProjectionInformation info = ProjectionInformation.get(type, projectionType);
                ArrayList<String> paths = new ArrayList<>();
                for (ProjectionAttribute attribute : info) {
                    paths.add(attribute.getFieldName());
                }
                Selection<?>[] selections = paths.stream()
                        .map(AttributePath::new)
                        .toArray(Selection[]::new);
                ConstantList<Selection<?>> array = new ConstantList<>(selections);
                CriteriaQueryImpl cq = CriteriaQueryImpl.from(criteriaQuery)
                        .updateSelection(array);
                List<Object[]> objects = getObjectsTypeQuery(cq, type)
                        .getObjectsList(offset, maxResult);
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
                            return JacksonMapper.writeValueAsString(proxy);
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
                        return null;
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
                return getTypeQuery(criteriaQuery, type).exist(offset);
            }
        };
    }

    ArrayResultQuery getObjectsTypeQuery(CriteriaQuery criteriaQuery, Class<?> type);


}
