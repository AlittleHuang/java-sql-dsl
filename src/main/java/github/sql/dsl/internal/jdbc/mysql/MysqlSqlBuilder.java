package github.sql.dsl.internal.jdbc.mysql;

import github.sql.dsl.criteria.query.expression.Expression;
import github.sql.dsl.criteria.query.expression.Operator;
import github.sql.dsl.criteria.query.expression.OperatorExpression;
import github.sql.dsl.criteria.query.expression.PathExpression;
import github.sql.dsl.criteria.query.support.CriteriaQuery;
import github.sql.dsl.criteria.query.support.builder.component.Order;
import github.sql.dsl.criteria.query.support.meta.Attribute;
import github.sql.dsl.criteria.query.support.meta.EntityInformation;
import github.sql.dsl.criteria.query.support.meta.ProjectionAttribute;
import github.sql.dsl.criteria.query.support.meta.ProjectionInformation;
import github.sql.dsl.internal.jdbc.sql.PreparedSql;
import github.sql.dsl.internal.jdbc.sql.PreparedSqlBuilder;
import github.sql.dsl.internal.jdbc.sql.SelectedPreparedSql;
import github.sql.dsl.util.Array;
import github.sql.dsl.util.Assert;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static github.sql.dsl.criteria.query.expression.Operator.*;

public class MysqlSqlBuilder implements PreparedSqlBuilder {

    protected final CriteriaQuery criteria;
    protected final EntityInformation<?> rootEntityInfo;

    public MysqlSqlBuilder(CriteriaQuery criteria, Class<?> javaType) {
        this.criteria = criteria;
        this.rootEntityInfo = getEntityInformation(javaType);
    }

    public static PathExpression<?> to(PathExpression<?> expression, String path) {
        String[] values = Stream.concat(expression.stream(), Stream.of(path))
                .toArray(String[]::new);
        return new PathExpression<>(values);
    }

    @Override
    public SelectedPreparedSql getEntityList(int offset, int maxResultant) {
        return new EntityBuilder().getEntityList(offset, maxResultant);
    }

    @Override
    public PreparedSql getObjectsList(int offset, int maxResultant) {
        return new Builder().getObjectsList(offset, maxResultant);
    }

    @Override
    public PreparedSql exist(int offset) {
        return new Builder().exist(offset);
    }

    @Override
    public PreparedSql count() {
        return new Builder().count();
    }

    @Override
    public SelectedPreparedSql getProjectionList(int offset, int maxResult, Class<?> projectionType) {
        return new ProjectionBuilder(projectionType).getProjectionList(offset, maxResult);
    }

    public EntityInformation<?> getEntityInformation(Attribute attribute) {
        return getEntityInformation(attribute.getJavaType());
    }

    public EntityInformation<?> getEntityInformation(Class<?> clazz) {
        EntityInformation<?> info = EntityInformation.getInstance(clazz);
        Assert.notNull(info, "the type " + clazz + " is not an entity type");
        return info;
    }

    private class ProjectionBuilder extends Builder implements SelectedPreparedSql {
        protected final List<PathExpression<?>> selectedPath = new ArrayList<>();
        private final Class<?> projectionType;

        private ProjectionBuilder(Class<?> projectionType) {
            this.projectionType = projectionType;
        }

        protected SelectedPreparedSql getProjectionList(int offset, int maxResult) {
            sql.append("select ");
            appendProjectionPath();
            appendQueryConditions(offset, maxResult);
            return this;
        }

        private void appendProjectionPath() {
            String join = "";
            ProjectionInformation attributes = ProjectionInformation
                    .get(rootEntityInfo.getJavaType(), projectionType);
            for (ProjectionAttribute basicAttribute : attributes) {
                sql.append(join);
                PathExpression<Object> path = new PathExpression<>(basicAttribute.getFieldName());
                appendPath(path);
                selectedPath.add(path);
                join = ",";
            }
        }

        @Override
        public List<PathExpression<?>> getSelectedPath() {
            return selectedPath;
        }
    }

    private class EntityBuilder extends Builder implements SelectedPreparedSql {
        protected final List<PathExpression<?>> selectedPath = new ArrayList<>();

        protected SelectedPreparedSql getEntityList(int offset, int maxResult) {
            sql.append("select ");
            appendEntityPath();
            appendFetchPath();
            appendQueryConditions(offset, maxResult);
            return this;
        }

        protected void appendEntityPath() {
            String join = "";
            for (Attribute basicAttribute : rootEntityInfo.getBasicAttributes()) {
                sql.append(join);
                PathExpression<Object> path = new PathExpression<>(basicAttribute.getFieldName());
                appendPath(path);
                selectedPath.add(path);
                join = ",";
            }
        }

        protected void appendFetchPath() {
            if (criteria.getFetchList() != null) {
                for (PathExpression<?> fetch : criteria.getFetchList()) {
                    Attribute attribute = getAttribute(fetch);
                    EntityInformation<?> entityInfo = getEntityInformation(attribute);
                    for (Attribute basicAttribute : entityInfo.getBasicAttributes()) {
                        sql.append(",");
                        PathExpression<?> path = to(fetch, basicAttribute.getFieldName());
                        appendPath(path);
                        selectedPath.add(path);
                    }
                }
            }
        }

        @Override
        public List<PathExpression<?>> getSelectedPath() {
            return selectedPath;
        }
    }

    protected class Builder implements PreparedSql {
        protected final StringBuilder sql = new StringBuilder();
        protected final List<Object> args = new ArrayList<>();
        protected final Map<PathExpression<?>, Integer> joins = new LinkedHashMap<>();

        protected PreparedSql getObjectsList(int offset, int maxResult) {
            sql.append("select ");
            appendSelectedPath();
            appendBlank()
                    .append("from `")
                    .append(rootEntityInfo.getTableName())
                    .append("` ");
            appendRootTableAlias();
            int sqlIndex = sql.length();
            appendWhere();
            appendGroupBy();
            appendOrderBy();
            limit(offset, maxResult);
            insertJoin(sqlIndex);
            return this;
        }

        protected void appendQueryConditions(int offset, int maxResult) {
            appendBlank()
                    .append("from `")
                    .append(rootEntityInfo.getTableName())
                    .append("` ");
            appendRootTableAlias();
            int sqlIndex = sql.length();
            appendWhere();
            appendOrderBy();
            limit(offset, maxResult);
            insertJoin(sqlIndex);
        }

        protected PreparedSql exist(int offset) {
            sql.append("select ");
            Attribute attribute = rootEntityInfo.getIdAttribute();
            appendRootTableAlias();
            sql.append(".`").append(attribute.getColumnName()).append("`");
            appendBlank()
                    .append("from `")
                    .append(rootEntityInfo.getTableName())
                    .append("` ");
            appendRootTableAlias();
            int sqlIndex = sql.length();
            appendWhere();
            limit(offset, -1);
            insertJoin(sqlIndex);
            return this;
        }

        protected PreparedSql count() {
            sql.append("select count(");
            Attribute attribute = rootEntityInfo.getIdAttribute();
            appendRootTableAlias();
            sql.append(".`").append(attribute.getColumnName()).append("`)");
            appendBlank()
                    .append("from `")
                    .append(rootEntityInfo.getTableName())
                    .append("` ");
            appendRootTableAlias();
            int sqlIndex = sql.length();
            appendWhere();
            insertJoin(sqlIndex);
            return this;
        }

        @Override
        public String getSql() {
            return sql.toString();
        }

        @Override
        public List<Object> getArgs() {
            return args;
        }

        protected StringBuilder appendRootTableAlias() {
            return appendRootTableAlias(sql);
        }

        protected StringBuilder appendRootTableAlias(StringBuilder sql) {
            return sql.append(rootEntityInfo.getTableName().charAt(0)).append("_r_");
        }

        protected StringBuilder appendBlank() {
            return sql.length() > 0 && " (".indexOf(sql.charAt(sql.length() - 1)) < 0
                    ? sql.append(' ')
                    : sql;
        }


        protected void appendWhere() {
            if (criteria.getRestriction() == null) {
                return;
            }
            sql.append(" where ");
            appendExpression(criteria.getRestriction());
        }

        protected void appendExpression(Expression<?> e) {
            appendExpressions(args, e);
        }


        protected void appendExpressions(List<Object> args, Expression<?> e) {
            if (e.getType() == Expression.Type.CONSTANT) {
                Object value = e.getValue();
                boolean isNumber = false;
                if (value != null) {
                    Class<?> valueType = value.getClass();
                    if (valueType.isPrimitive() || Number.class.isAssignableFrom(valueType)) {
                        isNumber = true;
                    }
                }
                if (isNumber) {
                    appendBlank().append(value);
                } else {
                    appendBlank().append('?');
                    args.add(value);
                }
            } else if (e.getType() == Expression.Type.PATH) {
                appendBlank();
                appendPath(e);
            } else if (e.getType() == Expression.Type.OPERATOR) {
                Operator operator = e.getOperator();
                List<? extends Expression<?>> list = e.getExpressions();
                Expression<?> e0 = list.get(0);
                Operator operator0 = getOperator(e0);
                if (NOT.equals(operator)) {
                    appendBlank().append(operator);
                    sql.append(' ');
                    if (operator0 != null && operator0.getPrecedence() > operator.getPrecedence()) {
                        sql.append('(');
                        appendExpressions(args, e0);
                        sql.append(')');
                    } else {
                        appendExpressions(args, e0);
                    }
                } else if (AND.equals(operator) || OR.equals(operator) || LIKE.equals(operator) || MOD.equals(operator) || GT.equals(operator) || EQ.equals(operator) || NE.equals(operator) || GE.equals(operator) || LT.equals(operator) || LE.equals(operator) || ADD.equals(operator) || SUBTRACT.equals(operator) || MULTIPLY.equals(operator) || DIVIDE.equals(operator)) {
                    appendBlank();
                    if (operator0 != null && operator0.getPrecedence() > operator.getPrecedence()) {
                        sql.append('(');
                        appendExpressions(args, e0);
                        sql.append(')');
                    } else {
                        appendExpressions(args, e0);
                    }

                    appendBlank();
                    sql.append(operator);
                    Expression<?> e1 = list.get(1);
                    Operator operator1 = getOperator(e1);
                    if (operator1 != null && operator1.getPrecedence() >= operator.getPrecedence()) {
                        sql.append('(');
                        appendExpressions(args, e1);
                        sql.append(')');
                    } else {
                        appendExpressions(args, e1);
                    }
                } else if (LOWER.equals(operator) || UPPER.equals(operator) || SUBSTRING.equals(operator) || TRIM.equals(operator) || LENGTH.equals(operator) || NULLIF.equals(operator) || IF_NULL.equals(operator) || ISNULL.equals(operator) || MIN.equals(operator) || MAX.equals(operator) || COUNT.equals(operator) || AVG.equals(operator) || SUM.equals(operator)) {
                    appendBlank().append(operator);
                    String join = "(";
                    for (Expression<?> expression : list) {
                        sql.append(join);
                        appendExpressions(args, expression);
                        join = ",";
                    }
                    sql.append(")");
                } else if (IN.equals(operator)) {
                    if (list.size() == 1) {
                        appendBlank().append(0);
                    } else {
                        appendBlank();
                        appendExpression(e0);

                        appendBlank().append(operator);
                        char join = '(';
                        for (int i = 1; i < list.size(); i++) {
                            Expression<?> expression = list.get(i);
                            sql.append(join);
                            appendExpressions(args, expression);
                            join = ',';
                        }
                        sql.append(")");
                    }
                } else if (BETWEEN.equals(operator)) {
                    appendBlank();
                    appendExpressions(args, list.get(0));
                    appendBlank().append(operator).append(" ");
                    appendExpressions(args, list.get(1).then(AND, list.get(2)));
                } else {
                    throw new UnsupportedOperationException("unknown operator " + operator);
                }
            } else {
                throw new UnsupportedOperationException("unknown expression type " + e.getClass());
            }
        }


        protected void appendPath(Expression<?> expression) {
            if (expression.getType() != Expression.Type.PATH) {
                throw new UnsupportedOperationException();
            }
            PathExpression<?> paths = expression.asPathExpression();
            StringBuilder sb = sql;
            int iMax = paths.size() - 1;
            if (iMax == -1)
                return;
            int i = 0;
            if (paths.size() == 1) {
                appendRootTableAlias().append(".");
            }
            Class<?> type = MysqlSqlBuilder.this.rootEntityInfo.getJavaType();

            PathExpression<?> join = new PathExpression<>(paths.get(0));

            for (String path : paths) {
                EntityInformation<?> info = getEntityInformation(type);
                Attribute attribute = info.getAttribute(path);
                if (i++ == iMax) {
                    sb.append('`').append(attribute.getColumnName()).append('`');
                    return;
                } else {
                    joins.putIfAbsent(join, joins.size());
                    if (i == iMax) {
                        Integer index = joins.get(join);
                        appendTableAlice(sb, attribute, index).append('.');
                    }
                }
                type = attribute.getJavaType();
                join = to(join, path);
            }
        }

        protected void insertJoin(int sqlIndex) {
            StringBuilder sql = new StringBuilder();

            joins.forEach((k, v) -> {
                Attribute attribute = getAttribute(k);
                EntityInformation<?> entityInfo = getEntityInformation(attribute);
                sql.append(" left join `").append(entityInfo.getTableName()).append("` ");

                appendTableAlice(sql, attribute, v);
                sql.append(" on ");
                PathExpression<?> parent = k.parent();
                if (parent == null) {
                    appendRootTableAlias(sql);
                } else {
                    Integer parentIndex = joins.get(parent);
                    Attribute parentAttribute = getAttribute(parent);
                    appendTableAlice(sql, parentAttribute, parentIndex);
                }
                sql.append(".`").append(attribute.getJoinColumn().name()).append("`=");
                appendTableAlice(sql, attribute, v);
                String referenced = attribute.getJoinColumn().referencedColumnName();
                if (referenced.length() == 0) {
                    referenced = entityInfo.getIdAttribute().getColumnName();
                }
                sql.append(".`").append(referenced).append('`');
            });
            this.sql.insert(sqlIndex, sql);

        }

        Operator getOperator(Expression<?> e) {
            if (e instanceof OperatorExpression) {
                return e.getOperator();
            }
            return null;
        }

        protected StringBuilder appendTableAlice(StringBuilder sb, Attribute attribute, Integer index) {
            EntityInformation<?> information = getEntityInformation(attribute.getJavaType());
            return sb.append(information.getTableName().charAt(0)).append("_j").append(index).append("_");
        }

        protected Attribute getAttribute(PathExpression<?> path) {
            Attribute attribute = null;
            for (String s : path.asPathExpression()) {
                EntityInformation<?> entityInfo = attribute == null
                        ? rootEntityInfo
                        : getEntityInformation(attribute);
                attribute = entityInfo.getAttribute(s);
            }
            return attribute;
        }

        protected void limit(int offset, int maxResults) {
            if (offset >= 0 || maxResults >= 0) {
                sql.append(" LIMIT ")
                        .append(Math.max(offset, 0))
                        .append(',')
                        .append(maxResults < 0 ? Long.MAX_VALUE : maxResults);
            }
        }

        protected void appendSelectedPath() {
            Iterable<Expression<?>> select = criteria.getSelectionList();
            if (select == null || !select.iterator().hasNext()) {
                select = rootEntityInfo.getBasicAttributes()
                        .stream()
                        .map(i -> {
                            String fieldName = i.getFieldName();
                            return new PathExpression<>(fieldName);
                        })
                        .collect(Collectors.toList());
            }
            String join = "";
            for (Expression<?> selection : select) {
                sql.append(join);
                appendExpression(selection);
                join = ",";
            }
        }


        private void appendGroupBy() {
            Array<Expression<?>> groupBy = criteria.getGroupList();
            if (groupBy != null && !groupBy.isEmpty()) {
                sql.append(" group by ");
                boolean first = true;
                for (Expression<?> e : groupBy) {
                    if (first) {
                        first = false;
                    } else {
                        sql.append(",");
                    }
                    appendExpression(e);
                }

            }
        }

        protected void appendOrderBy() {
            Array<Order> orders = criteria.getOrderList();
            if (orders != null && !orders.isEmpty()) {
                sql.append(" order by ");
                boolean first = true;
                for (Order order : orders) {
                    if (first) {
                        first = false;
                    } else {
                        sql.append(",");
                    }
                    appendExpression(order.getExpression());
                    sql.append(" ").append(order.isDesc() ? "desc" : "asc");
                }

            }
        }
    }

}