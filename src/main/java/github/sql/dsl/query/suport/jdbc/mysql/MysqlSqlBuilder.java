package github.sql.dsl.query.suport.jdbc.mysql;

import github.sql.dsl.query.api.*;
import github.sql.dsl.query.suport.common.model.CriteriaQuery;
import github.sql.dsl.query.suport.common.model.Order;
import github.sql.dsl.query.suport.jdbc.meta.Attribute;
import github.sql.dsl.query.suport.jdbc.meta.EntityInformation;
import github.sql.dsl.query.suport.jdbc.sql.EntityQueryPreparedSql;
import github.sql.dsl.query.suport.jdbc.sql.PreparedSql;
import github.sql.dsl.query.suport.jdbc.sql.PreparedSqlBuilder;
import github.sql.dsl.util.Assert;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.*;
import java.util.stream.Collectors;

public class MysqlSqlBuilder implements PreparedSqlBuilder {

    protected final CriteriaQuery criteria;
    protected final EntityInformation<?> rootEntityInfo;

    public MysqlSqlBuilder(CriteriaQuery criteria, Class<?> javaType) {
        this.criteria = criteria;
        this.rootEntityInfo = getEntityInformation(javaType);
    }

    @Override
    public EntityQueryPreparedSql getEntityList(int offset, int maxResultant) {
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

    private class EntityBuilder extends Builder implements EntityQueryPreparedSql {
        protected final List<PathExpression<?>> selectedPath = new ArrayList<>();

        protected EntityQueryPreparedSql getEntityList(int offset, int maxResult) {
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
            if (criteria.getFetch() != null) {
                for (PathExpression<?> fetch : criteria.getFetch()) {
                    Attribute attribute = getAttribute(fetch);
                    EntityInformation<?> entityInfo = getEntityInformation(attribute);
                    for (Attribute basicAttribute : entityInfo.getBasicAttributes()) {
                        sql.append(",");
                        PathExpression<?> path = fetch.to(basicAttribute.getFieldName());
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
            offset(offset);
            maxResult(maxResult);
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
            offset(offset);
            maxResult(maxResult);
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
            offset(offset);
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
                Operator<?> operator = e.getOperator();
                List<? extends Expression<?>> list = e.getExpressions();
                Expression<?> e0 = list.get(0);
                Operator<?> operator0 = getOperator(e0);
                if (operator == Operator.NOT) {
                    sql.append(operator);
                    sql.append(' ');
                    if (operator0 != null && operator0.getPrecedence() > operator.getPrecedence()) {
                        sql.append('(');
                        appendExpressions(args, e0);
                        sql.append(')');
                    } else {
                        appendExpressions(args, e0);
                    }
                } else if (aXb(operator)) {
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
                    Operator<?> operator1 = getOperator(e1);
                    if (operator1 != null && operator1.getPrecedence() >= operator.getPrecedence()) {
                        sql.append('(');
                        appendExpressions(args, e1);
                        sql.append(')');
                    } else {
                        appendExpressions(args, e1);
                    }

                } else if (operator == Operator.LOWER
                        || operator == Operator.UPPER
                        || operator == Operator.SUBSTRING
                        || operator == Operator.TRIM
                        || operator == Operator.LENGTH
                        || operator == Operator.IN
                        || operator == Operator.NULLIF
                        || operator == Operator.ISNULL) {
                    appendBlank().append(operator);
                    String join = "(";
                    for (Expression<?> expression : list) {
                        sql.append(join);
                        appendExpressions(args, expression);
                        join = ",";
                    }
                    sql.append(")");
                } else if (operator == Operator.BETWEEN) {
                    appendBlank();
                    appendExpressions(args, list.get(0));
                    appendBlank().append(operator).append(" (");
                    appendExpressions(args, list.get(1).then(Operator.AND, list.get(2)));
                    sql.append(")");
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
                join = join.to(path);
            }
        }

        protected boolean aXb(Operator<?> operator) {
            return operator == Operator.AND
                    || operator == Operator.OR
                    || operator == Operator.LIKE
                    || operator == Operator.MOD
                    || operator == Operator.GT
                    || operator == Operator.EQ
                    || operator == Operator.DIFF
                    || operator == Operator.GE
                    || operator == Operator.LT
                    || operator == Operator.LE
                    || operator == Operator.ADD
                    || operator == Operator.SUBTRACT
                    || operator == Operator.MULTIPLY
                    || operator == Operator.DIVIDE
                    ;
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

        Operator<?> getOperator(Expression<?> e) {
            if (e instanceof OperatorExpression) {
                return ((OperatorExpression<?>) e).getOperator();
            }
            return null;
        }

        protected StringBuilder appendTableAlice(StringBuilder sb, Attribute attribute, Integer index) {
            if (index == null) {
                System.out.println("???");

            }
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

        protected void offset(int offset) {
            if (offset > 0) {
                sql.append(" offset ").append(offset);
            }
        }

        protected void maxResult(int maxResult) {
            if (maxResult > 0) {
                sql.append(" limit ").append(maxResult);
            }
        }

        protected void appendSelectedPath() {
            List<Expression<?>> select = criteria.getSelection();
            if (select == null || select.isEmpty()) {
                select = rootEntityInfo.getBasicAttributes()
                        .stream()
                        .map(i -> {
                            String fieldName = i.getFieldName();
                            return new PathExpression<>(fieldName);
                        })
                        .collect(Collectors.toList());
            }
            String join = "";
            for (Expression<?> expression : select) {
                sql.append(join);
                appendExpression(expression);
                join = ",";
            }
        }


        private void appendGroupBy() {
            List<Expression<?>> groupBy = criteria.getGroupList();
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
            List<Order> orders = criteria.getOrderList();
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

    public EntityInformation<?> getEntityInformation(Attribute attribute) {
        return getEntityInformation(attribute.getJavaType());
    }

    public EntityInformation<?> getEntityInformation(Class<?> clazz) {
        EntityInformation<?> info = EntityInformation.getInstance(clazz);
        Assert.notNull(info, "the type " + clazz + " is not an entity type");
        return info;
    }


    public static void main(String[] args) {
        @Data
        class User implements Entity {

            int id;

            String username;

            Date time;

            Integer pid;

            @ManyToOne
            @JoinColumn(name = "pid")
            User parentUser;

        }

        Builder builder = new MysqlSqlBuilder(null, User.class).new Builder();
        Expression<?> test = new PathExpression<>("username");
        for (Operator<?> operator : Operator.list()) {
            test = test.then(operator, new PathExpression<>("username"), new PathExpression<>("username"));
        }
        builder.appendExpression(test);
        System.out.println(builder.getSql());
    }

}