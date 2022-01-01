package github.sql.dsl.query.suport.jdbc.sql;

import github.sql.dsl.query.suport.jdbc.util.JdbcUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@FunctionalInterface
public interface SqlExecutor {

    <T> T execute(ConnectionCallback<T> connectionCallback) throws SQLException;

    static SqlExecutor fromConnectionSupplier(ConnectionProvider supplier) {
        return new SqlExecutor() {
            @SneakyThrows
            public <T> T execute(ConnectionCallback<T> connectionCallback) {
                return connectionCallback.doInConnection(supplier.getConnection());
            }
        };
    }

    @SneakyThrows
    default int update(String sql, Object[] args) {
        SqlLogger.traceSql(sql, args);
        return execute(connection -> {
            PreparedStatement pst = connection.prepareStatement(sql);
            JdbcUtil.setParam(pst, args);
            return pst.executeUpdate();
        });
    }

    @SneakyThrows
    default int[] batchUpdate(String sql, List<Object[]> batchArgs) {
        SqlLogger.traceSql(sql, batchArgs);
        return execute(connection -> {
            PreparedStatement pst = connection.prepareStatement(sql);
            JdbcUtil.setParamBatch(pst, batchArgs);
            return pst.executeBatch();
        });
    }

    @SneakyThrows
    default <T> T query(String sql,
                        Object[] args,
                        ResultSetCallback<T> resultSetCallback) {
        SqlLogger.traceSql(sql, args);
        return execute(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql);
            JdbcUtil.setParam(statement, args);
            ResultSet resultSet = statement.executeQuery();
            return resultSetCallback.doInResultSet(resultSet);
        });
    }

    @SneakyThrows
    default <T> T insertAndReturnGeneratedKeys(String sql,
                                               Object[] args,
                                               ResultSetCallback<T> resultSetCallback) {
        SqlLogger.traceSql(sql, args);
        return execute(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            JdbcUtil.setParam(ps, args);
            ps.execute();
            ResultSet resultSet = ps.getGeneratedKeys();
            return resultSetCallback.doInResultSet(resultSet);
        });
    }

    @SneakyThrows
    default <T> T batchInsertAndReturnGeneratedKeys(String sql,
                                                    List<Object[]> batchArgs,
                                                    ResultSetCallback<T> resultSetCallback) {
        SqlLogger.traceSql(sql, batchArgs);
        return execute(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            JdbcUtil.setParamBatch(ps, batchArgs);
            ps.executeBatch();
            ResultSet resultSet = ps.getGeneratedKeys();
            return resultSetCallback.doInResultSet(resultSet);
        });
    }

    @FunctionalInterface
    interface ConnectionCallback<T> {
        @SuppressWarnings("unused")
        T doInConnection(Connection connection) throws SQLException;
    }

    @FunctionalInterface
    interface ResultSetCallback<T> {
        T doInResultSet(ResultSet connection) throws SQLException;
    }

    @Slf4j
    class SqlLogger {
        public static Function<String, String> sqlFormat = null;
        public static BiFunction<String, Object[], String> sqlArgsFormat = null;


        private static void traceSql(String sql, List<Object[]> args) {
            if (log.isDebugEnabled()) {
                if (sqlArgsFormat != null) {
                    for (Object[] arg : args) {
                        log.debug(sqlArgsFormat.apply(sql, arg));
                    }
                } else if (sqlFormat != null) {
                    log.debug(sqlFormat.apply(sql));
                } else {
                    log.debug(sql);
                }
            }
        }

        private static void traceSql(String sql, Object[] args) {
            if (log.isDebugEnabled()) {
                if (sqlArgsFormat != null) {
                    log.debug(sqlArgsFormat.apply(sql, args));
                } else if (sqlFormat != null) {
                    log.debug(sqlFormat.apply(sql));
                } else {
                    log.debug(sql);
                }
            }
        }
    }


}
