package github.sql.dsl.query.suport.common.model;

import github.sql.dsl.query.api.*;
import github.sql.dsl.query.api.BridgePath;
import github.sql.dsl.query.api.ConstantExpression;
import github.sql.dsl.query.api.Expression;
import github.sql.dsl.query.api.OperatorExpression;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public abstract class OperatorExpressionBuilder<T, X> implements PredicateBuilder<T, X> {

    protected final Expression<Boolean> restriction;

    public OperatorExpressionBuilder(Expression<Boolean> restriction) {
        Expression<Boolean> expression = null;
        if (restriction != null) {
            expression = restriction;
        }
        this.restriction = expression;
    }

    public Expression<Boolean> getRestriction() {
        return restriction;
    }

    protected abstract X exchangeWhere(OperatorExpression<Boolean> where);

    @Override
    public <U extends Entity> PathBuilder<T, U, X> and(EntityAttributeBridge<T, U> column) {
        return new Path<>(BridgePath.exchange(column), Operator.AND, false);
    }

    @Override
    public <U extends Entity> PathBuilder<T, U, X> or(EntityAttributeBridge<T, U> column) {
        return new Path<>(BridgePath.exchange(column), Operator.OR, false);
    }

    @Override
    public <U extends Entity> PathBuilder<T, U, X> andNot(EntityAttributeBridge<T, U> column) {
        return new Path<>(BridgePath.exchange(column), Operator.AND, true);
    }

    @Override
    public <U extends Entity> PathBuilder<T, U, X> orNot(EntityAttributeBridge<T, U> column) {
        return new Path<>(BridgePath.exchange(column), Operator.OR, true);
    }

    @Override
    public <U> ExpressionBuilder<T, U, X> and(AttributeBridge<T, U> attributeBridge) {
        return new Predicate<>(BridgePath.exchange(attributeBridge), Operator.AND, false);
    }

    @Override
    public <U> ExpressionBuilder<T, U, X> or(AttributeBridge<T, U> attributeBridge) {
        return new Predicate<>(BridgePath.exchange(attributeBridge), Operator.OR, false);
    }

    @Override
    public <U> ExpressionBuilder<T, U, X> andNot(AttributeBridge<T, U> attributeBridge) {
        return new Predicate<>(BridgePath.exchange(attributeBridge), Operator.AND, true);
    }

    @Override
    public <U> ExpressionBuilder<T, U, X> orNot(AttributeBridge<T, U> attributeBridge) {
        return new Predicate<>(BridgePath.exchange(attributeBridge), Operator.OR, true);
    }

    @Override
    public <U extends Number> NumberExpressionBuilder<T, U, X> and(NumberAttributeBridge<T, U> column) {
        return new NumberExpression<>(BridgePath.exchange(column), Operator.AND, false);
    }

    @Override
    public <U extends Number> NumberExpressionBuilder<T, U, X> or(NumberAttributeBridge<T, U> column) {
        return new NumberExpression<>(BridgePath.exchange(column), Operator.OR, false);
    }

    @Override
    public <U extends Number> NumberExpressionBuilder<T, U, X> andNot(NumberAttributeBridge<T, U> column) {
        return new NumberExpression<>(BridgePath.exchange(column), Operator.AND, true);
    }

    @Override
    public <U extends Number> NumberExpressionBuilder<T, U, X> orNot(NumberAttributeBridge<T, U> column) {
        return new NumberExpression<>(BridgePath.exchange(column), Operator.OR, true);
    }

    @Override
    public <U extends Date> ComparableExpressionBuilder<T, U, X> and(ComparableAttributeBridge<T, U> column) {
        return new ComparableExpression<>(BridgePath.exchange(column), Operator.AND, false);
    }

    @Override
    public <U extends Date> ComparableExpressionBuilder<T, U, X> or(ComparableAttributeBridge<T, U> column) {
        return new ComparableExpression<>(BridgePath.exchange(column), Operator.OR, false);
    }

    @Override
    public <U extends Date> ComparableExpressionBuilder<T, U, X> andNot(ComparableAttributeBridge<T, U> column) {
        return new ComparableExpression<>(BridgePath.exchange(column), Operator.AND, true);
    }

    @Override
    public <U extends Date> ComparableExpressionBuilder<T, U, X> orNot(ComparableAttributeBridge<T, U> column) {
        return new ComparableExpression<>(BridgePath.exchange(column), Operator.OR, true);
    }

    @Override
    public StringExpressionBuilder<T, X> and(StringAttributeBridge<T> column) {
        return new StringExpression(BridgePath.exchange(column), Operator.AND, false);
    }

    @Override
    public StringExpressionBuilder<T, X> or(StringAttributeBridge<T> column) {
        return new StringExpression(BridgePath.exchange(column), Operator.OR, false);
    }

    @Override
    public StringExpressionBuilder<T, X> andNot(StringAttributeBridge<T> column) {
        return new StringExpression(BridgePath.exchange(column), Operator.AND, true);
    }

    @Override
    public StringExpressionBuilder<T, X> orNot(StringAttributeBridge<T> column) {
        return new StringExpression(BridgePath.exchange(column), Operator.OR, true);
    }

    class BaseCondition<U> {

        protected final Expression<?> expression;
        protected final Operator<Boolean> combined;
        protected final boolean negate;

        private BaseCondition(Expression<?> expression, Operator<Boolean> combined, boolean negate) {
            this.expression = expression;
            this.combined = combined;
            this.negate = negate;
        }

        public <V extends Entity> PathBuilder<T, V, X> to(EntityAttributeBridge<U, V> column) {
            if (expression instanceof BridgePath) {
                Expression<?> newColumn = ((BridgePath<?, ?>) expression).to(column);
                return new Path<>(newColumn, combined, negate);
            }
            throw new UnsupportedOperationException();
        }

        public <V extends Number> NumberExpressionBuilder<T, V, X> to(NumberAttributeBridge<U, V> column) {
            if (expression instanceof BridgePath) {
                Expression<?> newColumn = ((BridgePath<?, ?>) expression).to(column);
                return new NumberExpression<>(newColumn, combined, negate);
            }
            throw new UnsupportedOperationException();
        }

        public <V extends Date> ComparableExpressionBuilder<T, V, X> to(ComparableAttributeBridge<U, V> column) {
            if (expression instanceof BridgePath) {
                Expression<?> newColumn = ((BridgePath<?, ?>) expression).to(column);
                return new ComparableExpression<>(newColumn, combined, negate);
            }
            throw new UnsupportedOperationException();
        }

        public <V extends Date> ExpressionBuilder<T, V, X> to(AttributeBridge<U, V> attributeBridge) {
            if (expression instanceof BridgePath) {
                Expression<?> newColumn = ((BridgePath<?, ?>) expression).to(attributeBridge);
                return new Predicate<>(newColumn, combined, negate);
            }
            throw new UnsupportedOperationException();
        }

        public StringExpressionBuilder<T, X> to(StringAttributeBridge<U> column) {
            if (expression instanceof BridgePath) {
                Expression<?> newColumn = ((BridgePath<?, ?>) expression).to(column);
                return new StringExpression(newColumn, combined, negate);
            }
            throw new UnsupportedOperationException();
        }

        public X isNull() {
            return addWhereExpressions(Operator.ISNULL, Collections.emptyList());
        }

        @NotNull
        protected X addWhereExpressions(Operator<Boolean> opt, Object value) {
            Expression<?> expression;
            if (value instanceof Expression) {
                expression = (Expression<?>) value;
            } else if (value instanceof AttributeBridge) {
                expression = BridgePath.asExpression((AttributeBridge<?, ?>) value);
            } else {
                expression = new ConstantExpression<>(value);
            }
            return addWhereExpressions(opt, Collections.singletonList(expression));
        }

        @NotNull
        protected X addWhereExpressions(Operator<Boolean> operator,
                                        List<? extends Expression<?>> expressions) {
            OperatorExpression<Boolean> add = expression.then(operator, expressions);
            OperatorExpression<Boolean> result;
            if (restriction == null) {
                result = add;
            } else {
                result = restriction.then(combined, add);
            }
            if (negate) {
                result = result.then(Operator.NOT);
            }

            return exchangeWhere(result);
        }

        public X eq(U value) {
            return addWhereExpressions(Operator.EQ, value);
        }

        public X diff(U value) {
            return addWhereExpressions(Operator.DIFF, value);
        }

        public X in(Collection<U> values) {
            List<ConstantExpression<U>> expressions = values.stream()
                    .map(ConstantExpression::new)
                    .collect(Collectors.toList());
            return addWhereExpressions(Operator.IN, expressions);

        }

        public X ge(U value) {
            return addWhereExpressions(Operator.GE, value);
        }

        public X gt(U value) {
            return addWhereExpressions(Operator.GT, value);
        }

        public X le(U value) {
            return addWhereExpressions(Operator.LE, value);
        }

        public X between(U a, U b) {
            List<ConstantExpression<U>> expressions = Arrays.asList(
                    new ConstantExpression<>(a),
                    new ConstantExpression<>(b)
            );

            return addWhereExpressions(Operator.BETWEEN, expressions);
        }

        public X lt(U value) {
            return addWhereExpressions(Operator.LT, value);
        }

        public X ge(AttributeBridge<T, U> value) {
            return addWhereExpressions(Operator.GE, value);
        }

        public X gt(AttributeBridge<T, U> value) {
            return addWhereExpressions(Operator.GT, value);
        }

        public X le(AttributeBridge<T, U> value) {
            return addWhereExpressions(Operator.LE, value);
        }

        public X between(AttributeBridge<T, U> a, AttributeBridge<T, U> b) {
            List<Expression<?>> expressions = Arrays.asList(
                    BridgePath.asExpression(a),
                    BridgePath.asExpression(b)
            );
            return addWhereExpressions(Operator.LE, expressions);
        }

        public X lt(AttributeBridge<T, U> value) {
            return addWhereExpressions(Operator.LT, value);
        }

        public X ge(Expression<U> value) {
            return addWhereExpressions(Operator.GE, value);
        }

        public X gt(Expression<U> value) {
            return addWhereExpressions(Operator.GT, value);
        }

        public X le(Expression<U> value) {
            return addWhereExpressions(Operator.LE, value);
        }

        public X between(Expression<U> a, Expression<U> b) {
            List<Expression<?>> expressions = Arrays.asList(a, b);
            return addWhereExpressions(Operator.LE, expressions);
        }

        public X lt(Expression<U> value) {
            return addWhereExpressions(Operator.LE, value);
        }

    }

    class Predicate<U> extends BaseCondition<U>
            implements ExpressionBuilder<T, U, X> {

        private Predicate(Expression<?> expression, Operator<Boolean> operator, boolean negate) {
            super(expression, operator, negate);
        }

        @Override
        public Predicate<U> nullIf(U value) {
            OperatorExpression<Object> model = expression.then(Operator.NULLIF, new ConstantExpression<>(value));
            return new Predicate<>(model, combined, negate);
        }

    }

    class ComparableExpression<U extends Comparable<?>>
            extends BaseCondition<U>
            implements ComparableExpressionBuilder<T, U, X> {

        private ComparableExpression(Expression<?> expression, Operator<Boolean> operator, boolean negate) {
            super(expression, operator, negate);
        }

        @Override
        public ComparableExpressionBuilder<T, U, X> nullIf(U value) {
            OperatorExpression<Object> model = expression.then(Operator.NULLIF, new ConstantExpression<>(value));
            return new ComparableExpression<>(model, combined, negate);
        }

    }

    class Path<U extends Entity>
            extends BaseCondition<U>
            implements PathBuilder<T, U, X> {

        public Path(Expression<?> expression, Operator<Boolean> operator, boolean negate) {
            super(expression, operator, negate);
        }


    }

    class StringExpression
            extends BaseCondition<String>
            implements StringExpressionBuilder<T, X> {

        private StringExpression(Expression<?> expression, Operator<Boolean> operator, boolean negate) {
            super(expression, operator, negate);
        }

        @Override
        public X like(String value) {
            return addWhereExpressions(Operator.LIKE, value);
        }

        @Override
        public X startWith(String value) {
            return like(value + "%");
        }

        @Override
        public X startEndWith(String value) {
            return like("%" + value);
        }

        @Override
        public X contains(String value) {
            return like("%" + value + "%");
        }

        @Override
        public StringExpressionBuilder<T, X> nullIf(String value) {
            OperatorExpression<?> model = expression.then(Operator.NULLIF, new ConstantExpression<>(value));
            return new StringExpression(model, combined, negate);
        }

        @Override
        public StringExpressionBuilder<T, X> lower() {
            OperatorExpression<?> model = expression.then(Operator.LOWER);
            return new StringExpression(model, combined, negate);
        }

        @Override
        public StringExpressionBuilder<T, X> upper() {
            OperatorExpression<?> model = expression.then(Operator.UPPER);
            return new StringExpression(model, combined, negate);
        }

        @Override
        public StringExpressionBuilder<T, X> substring(int a, int b) {
            OperatorExpression<?> model = expression.then(
                    Operator.SUBSTRING,
                    new ConstantExpression<>(a),
                    new ConstantExpression<>(b)
            );
            return new StringExpression(model, combined, negate);
        }

        @Override
        public StringExpressionBuilder<T, X> substring(int a) {
            OperatorExpression<?> model = expression.then(
                    Operator.SUBSTRING,
                    new ConstantExpression<>(a)
            );
            return new StringExpression(model, combined, negate);
        }

        @Override
        public StringExpressionBuilder<T, X> trim() {
            OperatorExpression<?> model = expression.then(Operator.TRIM);
            return new StringExpression(model, combined, negate);
        }

        @Override
        public NumberExpressionBuilder<T, Integer, X> length() {
            OperatorExpression<?> model = expression.then(Operator.LENGTH);
            return new NumberExpression<>(model, combined, negate);
        }

    }

    class NumberExpression<U extends Number>
            extends BaseCondition<U>
            implements NumberExpressionBuilder<T, U, X> {

        private NumberExpression(Expression<?> expression, Operator<Boolean> operator, boolean negate) {
            super(expression, operator, negate);
        }

        @Override
        public NumberExpressionBuilder<T, U, X> add(U v) {
            OperatorExpression<?> model = expression.then(Operator.ADD, new ConstantExpression<>(v));
            return new NumberExpression<>(model, combined, negate);
        }

        @Override
        public NumberExpressionBuilder<T, U, X> subtract(U v) {
            OperatorExpression<?> model = expression.then(Operator.SUBTRACT, new ConstantExpression<>(v));
            return new NumberExpression<>(model, combined, negate);
        }

        @Override
        public NumberExpressionBuilder<T, U, X> multiply(U v) {
            OperatorExpression<?> model = expression.then(Operator.MULTIPLY, new ConstantExpression<>(v));
            return new NumberExpression<>(model, combined, negate);
        }

        @Override
        public NumberExpressionBuilder<T, U, X> divide(U v) {
            OperatorExpression<?> model = expression.then(Operator.DIVIDE, new ConstantExpression<>(v));
            return new NumberExpression<>(model, combined, negate);
        }

        @Override
        public NumberExpressionBuilder<T, U, X> mod(U v) {
            OperatorExpression<?> model = expression.then(Operator.MOD, new ConstantExpression<>(v));
            return new NumberExpression<>(model, combined, negate);
        }

        @Override
        public NumberExpressionBuilder<T, U, X> nullIf(U value) {
            OperatorExpression<?> model = expression.then(Operator.MOD, new ConstantExpression<>(value));
            return new NumberExpression<>(model, combined, negate);
        }

        @Override
        public NumberExpressionBuilder<T, U, X> add(NumberAttributeBridge<T, U> v) {
            OperatorExpression<?> model = expression.then(Operator.ADD, BridgePath.asExpression(v));
            return new NumberExpression<>(model, combined, negate);
        }

        @Override
        public NumberExpressionBuilder<T, U, X> subtract(NumberAttributeBridge<T, U> v) {
            OperatorExpression<?> model = expression.then(Operator.SUBTRACT, BridgePath.asExpression(v));
            return new NumberExpression<>(model, combined, negate);
        }

        @Override
        public NumberExpressionBuilder<T, U, X> multiply(NumberAttributeBridge<T, U> v) {
            OperatorExpression<?> model = expression.then(Operator.MULTIPLY, BridgePath.asExpression(v));
            return new NumberExpression<>(model, combined, negate);
        }

        @Override
        public NumberExpressionBuilder<T, U, X> divide(NumberAttributeBridge<T, U> v) {
            OperatorExpression<?> model = expression.then(Operator.DIVIDE, BridgePath.asExpression(v));
            return new NumberExpression<>(model, combined, negate);
        }

        @Override
        public NumberExpressionBuilder<T, U, X> mod(NumberAttributeBridge<T, U> v) {
            OperatorExpression<?> model = expression.then(Operator.MOD, BridgePath.asExpression(v));
            return new NumberExpression<>(model, combined, negate);
        }

        @Override
        public X ge(NumberAttributeBridge<T, U> value) {
            return ge((AttributeBridge<T, U>) value);
        }

        @Override
        public X gt(NumberAttributeBridge<T, U> value) {
            return gt((AttributeBridge<T, U>) value);
        }

        @Override
        public X le(NumberAttributeBridge<T, U> value) {
            return le((AttributeBridge<T, U>) value);
        }

        @Override
        public X between(NumberAttributeBridge<T, U> a, NumberAttributeBridge<T, U> b) {
            return between((AttributeBridge<T, U>) a, b);
        }

        @Override
        public X lt(NumberAttributeBridge<T, U> value) {
            return lt((AttributeBridge<T, U>) value);
        }

        @Override
        public NumberExpressionBuilder<T, U, X> add(Expression<U> v) {
            OperatorExpression<?> model = expression.then(Operator.ADD, v);
            return new NumberExpression<>(model, combined, negate);
        }

        @Override
        public NumberExpressionBuilder<T, U, X> subtract(Expression<U> v) {
            OperatorExpression<?> model = expression.then(Operator.SUBTRACT, v);
            return new NumberExpression<>(model, combined, negate);
        }

        @Override
        public NumberExpressionBuilder<T, U, X> multiply(Expression<U> v) {
            OperatorExpression<?> model = expression.then(Operator.MULTIPLY, v);
            return new NumberExpression<>(model, combined, negate);
        }

        @Override
        public NumberExpressionBuilder<T, U, X> divide(Expression<U> v) {
            OperatorExpression<?> model = expression.then(Operator.DIVIDE, v);
            return new NumberExpression<>(model, combined, negate);
        }

        @Override
        public NumberExpressionBuilder<T, U, X> mod(Expression<U> v) {
            OperatorExpression<?> model = expression.then(Operator.MOD, v);
            return new NumberExpression<>(model, combined, negate);
        }

    }

}
