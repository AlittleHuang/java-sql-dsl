package github.sql.dsl.query.suport.jdbc.sql;

import github.sql.dsl.query.api.PathExpression;
import github.sql.dsl.query.suport.jdbc.meta.Attribute;
import github.sql.dsl.query.suport.jdbc.meta.EntityInformation;
import github.sql.dsl.query.suport.jdbc.util.JdbcUtil;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlExecutorImpl implements PreparedSqlExecutor {


    protected final SqlExecutor sqlExecutor;

    public SqlExecutorImpl(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public <T> List<T> getEntityList(EntityQueryPreparedSql sql, Class<T> entityType) {
        return getResultSet(sql, resultSet -> mapToEntity(sql, entityType, resultSet));
    }

    @SneakyThrows
    @NotNull
    private <T> List<T> mapToEntity(EntityQueryPreparedSql sql, Class<T> type, ResultSet resultSet) {
        List<T> result = new ArrayList<>();
        int columnsCount = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            List<PathExpression<?>> selectedPath = sql.getSelectedPath();
            T row = type.newInstance();
            for (int i = 0; i < columnsCount; i++) {
                PathExpression<?> path = selectedPath.get(i);
                PathExpression<?> pathList = path.asPathExpression();
                int size = pathList.size();
                EntityInformation<T> info = EntityInformation.getInstance(type);
                Object entity = row;
                if (resultSet.getObject(i + 1) != null) {
                    for (int j = 0; j < size; j++) {
                        Attribute attribute = info.getAttribute(pathList.get(j));
                        if (j == size - 1) {
                            Object value = JdbcUtil.getValue(resultSet, i + 1, attribute.getJavaType());
                            attribute.setValue(entity, value);
                        } else {
                            Object next = attribute.getValue(entity);
                            if (next == null) {
                                next = attribute.getJavaType().newInstance();
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
}
