package github.sql.dsl.query.suport;

import github.sql.dsl.query.api.builder.ArrayResultQuery;
import github.sql.dsl.query.api.builder.EntityResultQuery;
import github.sql.dsl.query.api.builder.ProjectionResultQuery;
import github.sql.dsl.query.api.expression.path.AttributePath;
import github.sql.dsl.query.suport.builder.component.ConstantList;
import github.sql.dsl.query.suport.builder.component.Selection;
import github.sql.dsl.query.suport.builder.query.CriteriaQueryImpl;
import github.sql.dsl.query.suport.jdbc.util.JacksonMapper;
import github.sql.dsl.query.suport.meta.ProjectionAttribute;
import github.sql.dsl.query.suport.meta.ProjectionInformation;
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
                        .map(os -> mapToRejection(info, paths, os))
                        .collect(Collectors.toList());
            }

            @SneakyThrows
            private R mapToRejection(ProjectionInformation info, ArrayList<String> paths, Object[] os) {
                ClassLoader classLoader = projectionType.getClassLoader();
                Class<?>[] interfaces = {projectionType};

                if (projectionType.isInterface()) {
                    Map<Method, Object> row = new HashMap<>();
                    int i = 0;
                    for (ProjectionAttribute attribute : info) {
                        Object value = os[i++];
                        row.put(attribute.getGetter(), value);
                    }

                    //noinspection unchecked
                    return (R) Proxy.newProxyInstance(classLoader, interfaces, (proxy, method, args) -> {
                        if (row.containsKey(method)) {
                            return row.get(method);
                        }
                        if (method.getName().equals("toString") && method.getParameterTypes().length == 0) {
                            return JacksonMapper.writeValueAsString(proxy);
                        }
                        return method.invoke(row, args);
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
