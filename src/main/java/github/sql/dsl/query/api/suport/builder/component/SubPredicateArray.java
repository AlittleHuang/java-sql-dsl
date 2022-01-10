package github.sql.dsl.query.api.suport.builder.component;

import github.sql.dsl.query.api.expression.ConstantExpression;
import github.sql.dsl.query.api.expression.Expression;
import github.sql.dsl.query.api.expression.Operator;
import github.sql.dsl.query.api.expression.PathExpression;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SubPredicateArray implements Expression<Boolean> {

    private final ConstantList<SubPredicate> values;
    private Expression<Boolean> value;

    public static SubPredicateArray fromExpression(Expression<Boolean> expression) {
        if (expression == null) {
            return null;
        }
        if (expression instanceof SubPredicateArray) {
            return (SubPredicateArray) expression;
        }
        ConstantList<SubPredicate> values =
                new ConstantList<>(new SubPredicate(expression, Operator.AND, false));
        return new SubPredicateArray(values);
    }

    public SubPredicateArray(ConstantList<SubPredicate> values) {
        this.values = values;
    }

    private Expression<Boolean> value() {
        return value != null ? value : (value = merge());
    }

    private Expression<Boolean> merge() {
        if (values == null || values.isEmpty()) {
            return new ConstantExpression<>(false);
        }
        if (values.size() == 1) {
            return values.get(0).getExpression();
        }

        SubPredicate[] arr = values.stream()
                .map(i -> {
                    if (i.isNegate()) {
                        return new SubPredicate(i.getExpression().then(Operator.NOT), i.getCombined(), false);
                    }
                    return i;
                })
                .toArray(SubPredicate[]::new);
        boolean and = true;
        while (true) {
            if (and) {
                and = false;
                for (int i = 1; i < arr.length; i++) {
                    SubPredicate vi = arr[i];
                    if (vi == null) {
                        continue;
                    }
                    Operator combined = vi.getCombined();
                    if (combined == Operator.AND) {
                        and = true;
                        arr[i] = null;
                        for (int j = i - 1; j >= 0; j--) {
                            SubPredicate vj = arr[j];
                            if (vj == null) {
                                continue;
                            }
                            Expression<Boolean> updated = vj.getExpression().then(combined, vi.getExpression());
                            arr[j] = new SubPredicate(updated, vj.getCombined(), vj.isNegate());
                            break;
                        }
                    } else if (combined != Operator.OR) {
                        throw new UnsupportedOperationException();
                    }
                }
            } else {
                Expression<Boolean> result = arr[0].getExpression();
                for (int i = 1; i < arr.length; i++) {
                    if (arr[i] != null) {
                        result = result.then(arr[i].getCombined(), arr[i].getExpression());
                    }
                }
                return result;
            }
        }
    }

    @Override
    public Expression<Boolean> unwrap() {
        return value();
    }

    @Override
    public PathExpression<Boolean> asPathExpression() {
        return value().asPathExpression();
    }

    @Override
    public Type getType() {
        return value().getType();
    }

    @Override
    public Boolean getValue() {
        return value().getValue();
    }

    @Override
    public Operator getOperator() {
        return value().getOperator();
    }

    @Override
    public List<? extends Expression<?>> getExpressions() {
        return value().getExpressions();
    }

    @Override
    public <X> Expression<X> then(Operator operator, Object... args) {
        return then(operator, Arrays.asList(args));
    }

    @Override
    public <X> Expression<X> then(Operator operator, @NotNull Collection<?> args) {
        if (operator == Operator.AND || operator == Operator.OR) {
            Object next = args.iterator().next();
            //noinspection unchecked
            Expression<Boolean> of = (Expression<Boolean>) Expression.of(next);
            //noinspection unchecked
            return (Expression<X>) new SubPredicateArray(values.concat(new SubPredicate(of, operator, false)));
        }
        return Expression.super.then(operator, args);
    }


}