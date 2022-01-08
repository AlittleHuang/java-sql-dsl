package github.sql.dsl.query.suport.jdbc.sql;

import github.sql.dsl.query.api.expression.PathExpression;
import github.sql.dsl.query.suport.jdbc.meta.Attribute;
import github.sql.dsl.query.suport.jdbc.meta.EntityInformation;
import github.sql.dsl.query.suport.jdbc.meta.ProjectionAttribute;
import github.sql.dsl.query.suport.jdbc.meta.ProjectionInformation;
import github.sql.dsl.query.suport.jdbc.util.JacksonMapper;
import github.sql.dsl.query.suport.jdbc.util.JdbcUtil;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlExecutorImpl implements PreparedSqlExecutor {

    protected final SqlExecutor sqlExecutor;

    public SqlExecutorImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public <T> List<T> getEntityList(SelectedPreparedSql sql, Class<T> entityType) {
        return getResultSet(sql, resultSet -> mapToEntity(sql, entityType, resultSet));
    }

    @Override
    public <T, R> List<R> getProjectionList(SelectedPreparedSql sql, Class<T> entityType, Class<R> projectionType) {

        return getResultSet(sql, resultSet -> projection(resultSet, sql, entityType, projectionType));
    }

    @SneakyThrows
    private <T, R> List<R> projection(ResultSet resultSet, SelectedPreparedSql sql, Class<T> entityType, Class<R> projectionType) {
        ProjectionInformation info = ProjectionInformation.get(entityType, projectionType);
        List<R> result = new ArrayList<>();

        int columnsCount = resultSet.getMetaData().getColumnCount();
        if (projectionType.isInterface()) {
            Class<?>[] interfaces = {projectionType};
            ClassLoader classLoader = projectionType.getClassLoader();

            while (resultSet.next()) {
                List<PathExpression<?>> selectedPath = sql.getSelectedPath();
                Map<Method, Object> row = new HashMap<>();
                for (int i = 0; i < columnsCount; i++) {
                    PathExpression<?> path = selectedPath.get(i);
                    int size = path.size();
                    if (resultSet.getObject(i + 1) != null) {
                        for (int j = 0; j < size; j++) {
                            String name = path.get(j);
                            ProjectionAttribute attribute = info.get(name);
                            if (j == size - 1) {
                                Object value = JdbcUtil.getValue(resultSet, i + 1, attribute.getJavaType());
                                row.put(attribute.getGetter(), value);
                            } else {
                                throw new UnsupportedOperationException();
                            }
                        }
                    }
                }
                //noinspection unchecked
                R r = (R) Proxy.newProxyInstance(classLoader, interfaces, (proxy, method, args) -> {
                    if (row.containsKey(method)) {
                        return row.get(method);
                    }
                    if (method.getName().equals("toString") && method.getParameterTypes().length == 0) {
                        return JacksonMapper.writeValueAsString(proxy);
                    }
                    return method.invoke(row, args);
                });

                result.add(r);
            }
        } else {
            while (resultSet.next()) {
                List<PathExpression<?>> selectedPath = sql.getSelectedPath();
                R row = projectionType.getConstructor().newInstance();
                for (int i = 0; i < columnsCount; i++) {
                    PathExpression<?> path = selectedPath.get(i);
                    int size = path.size();
                    if (resultSet.getObject(i + 1) != null) {
                        for (int j = 0; j < size; j++) {
                            String name = path.get(j);
                            ProjectionAttribute attribute = info.get(name);
                            if (j == size - 1) {
                                Object value = JdbcUtil.getValue(resultSet, i + 1, attribute.getJavaType());
                                attribute.setValue(row, value);
                            } else {
                                throw new UnsupportedOperationException();
                            }
                        }
                    }
                }
                result.add(row);
            }
        }
        return result;
    }

    @SneakyThrows
    @NotNull
    private <T> List<T> mapToEntity(SelectedPreparedSql sql, Class<T> type, ResultSet resultSet) {
        List<T> result = new ArrayList<>();
        int columnsCount = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            List<PathExpression<?>> selectedPath = sql.getSelectedPath();
            T row = type.getConstructor().newInstance();
            for (int i = 0; i < columnsCount; i++) {
                PathExpression<?> path = selectedPath.get(i);
                int size = path.size();
                EntityInformation<T> info = EntityInformation.getInstance(type);
                Object entity = row;
                if (resultSet.getObject(i + 1) != null) {
                    for (int j = 0; j < size; j++) {
                        Attribute attribute = info.getAttribute(path.get(j));
                        if (j == size - 1) {
                            Object value = JdbcUtil.getValue(resultSet, i + 1, attribute.getJavaType());
                            attribute.setValue(entity, value);
                        } else {
                            Object next = attribute.getValue(entity);
                            if (next == null) {
                                next = attribute.getJavaType().getConstructor().newInstance();
                                attribute.setValue(entity, next);
                            }
                            entity = next;
                        }
                    }
                }
            }
            result.add(row);
        }
        return result;
    }

    @Override
    public List<Object[]> listResult(PreparedSql sql, Class<?> entityType) {
        return getResultSet(sql, resultSet -> {
            List<Object[]> result = new ArrayList<>();
            int columnsCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                Object[] row = new Object[columnsCount];
                for (int i = 0; i < columnsCount; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                result.add(row);
            }
            return result;
        });
    }

    @Override
    public boolean exist(PreparedSql sql, Class<?> entityType) {
        return getResultSet(sql, ResultSet::next);
    }

    @Override
    public int count(PreparedSql sql, Class<?> entityType) {
        return getResultSet(sql, resultSet -> {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;
        });
    }


    private <T> T getResultSet(PreparedSql sql, SqlExecutor.ResultSetCallback<T> callback) {
        return sqlExecutor.query(sql.getSql(), sql.getArgs().toArray(), callback);
    }

    public static void main(String[] args) {
        System.out.println(new Object().toString());
    }
}
