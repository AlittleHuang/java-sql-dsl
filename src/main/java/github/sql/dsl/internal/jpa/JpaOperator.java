package github.sql.dsl.internal.jpa;

import github.sql.dsl.criteria.query.expression.Operator;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@Getter
public abstract class JpaOperator {

    private static final AtomicInteger id = new AtomicInteger();

    public static final JpaOperator NOT = new JpaOperator() {
        @Override
        public Predicate operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.not(cast(expressions.get(0)));
        }
    };

    public static final JpaOperator AND = new JpaOperator() {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            Predicate[] restrictions = expressions.stream()
                    .map(JpaOperator::<Boolean>cast)
                    .map(mapToPredicate(cb))
                    .toArray(Predicate[]::new);
            return cb.and(restrictions);
        }
    };

    public static final JpaOperator OR = new JpaOperator() {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            Predicate[] restrictions = expressions.stream()
                    .map(JpaOperator::<Boolean>cast)
                    .map(mapToPredicate(cb))
                    .toArray(Predicate[]::new);
            return cb.or(restrictions);
        }
    };
    public static final JpaOperator GT = new JpaOperator() {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.greaterThan(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };
    public static final JpaOperator EQ = new JpaOperator() {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.equal(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };
    public static final JpaOperator NE = new JpaOperator() {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.notEqual(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };
    public static final JpaOperator GE = new JpaOperator() {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.greaterThanOrEqualTo(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };
    public static final JpaOperator LT = new JpaOperator() {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.lessThan(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };
    public static final JpaOperator LE = new JpaOperator() {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.lessThanOrEqualTo(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };
    public static final JpaOperator LIKE = new JpaOperator() {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.like(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };

    public static final JpaOperator ISNULL = new JpaOperator() {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.isNull(cast(expressions.get(0)));
        }
    };
    public static final JpaOperator IN = new JpaOperator() {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            if (expressions.size() > 1) {
                CriteriaBuilder.In<Object> in = cb.in(expressions.get(0));
                for (int i = 1; i < expressions.size(); i++) {
                    in = in.value(expressions.get(i));
                }
                return in;
            } else {
                return cb.literal(false);
            }
        }
    };
    public static final JpaOperator BETWEEN = new JpaOperator() {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.between(cast(expressions.get(0)), cast(expressions.get(1)), cast(expressions.get(2)));
        }
    };

    public static final JpaOperator LOWER = new JpaOperator() {
        @Override
        public Expression<String> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.lower(cast(expressions.get(0)));
        }
    };
    public static final JpaOperator UPPER = new JpaOperator() {
        @Override
        public Expression<String> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.upper(cast(expressions.get(0)));
        }
    };
    public static final JpaOperator SUBSTRING = new JpaOperator() {
        @Override
        public Expression<String> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            Expression<String> e0 = cast(expressions.get(0));
            if (expressions.size() == 2) {
                return cb.substring(e0, expressions.get(1).as(Integer.class));
            } else if (expressions.size() > 2) {
                return cb.substring(e0, cast(expressions.get(1)), cast(expressions.get(2)));
            } else {
                throw new IllegalArgumentException("argument length error");
            }
        }
    };
    public static final JpaOperator TRIM = new JpaOperator() {
        @Override
        public Expression<String> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.trim(cast(expressions.get(0)));
        }
    };
    public static final JpaOperator LENGTH = new JpaOperator() {
        @Override
        public Expression<Integer> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.length(cast(expressions.get(0)));
        }
    };

    public static final JpaOperator ADD = new JpaOperator() {
        @Override
        public Expression<Number> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.sum(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };
    public static final JpaOperator SUBTRACT = new JpaOperator() {
        @Override
        public Expression<Number> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.diff(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };
    public static final JpaOperator MULTIPLY = new JpaOperator() {
        @Override
        public Expression<Number> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.prod(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };
    public static final JpaOperator DIVIDE = new JpaOperator() {
        @Override
        public Expression<Number> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.quot(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };
    public static final JpaOperator MOD = new JpaOperator() {
        @Override
        public Expression<Integer> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.mod(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };
    public static final JpaOperator NULLIF = new JpaOperator() {
        @Override
        public Expression<Object> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cast(cb.nullif(expressions.get(0), expressions.get(1)));
        }
    };
    public static final JpaOperator IF_NULL = new JpaOperator() {
        @Override
        public Expression<Object> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cast(cb.coalesce(expressions.get(0), expressions.get(1)));
        }
    };


    //aggregate function
    public static final JpaOperator MIN = new JpaOperator() {
        @Override
        public Expression<Number> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.min(cast(expressions.get(0)));
        }
    };

    public static final JpaOperator MAX = new JpaOperator() {
        @Override
        public Expression<Number> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.max(cast(expressions.get(0)));
        }
    };
    public static final JpaOperator COUNT = new JpaOperator() {
        @Override
        public Expression<Long> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.count(cast(expressions.get(0)));
        }
    };
    public static final JpaOperator AVG = new JpaOperator() {
        @Override
        public Expression<Double> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.avg(cast(expressions.get(0)));
        }
    };
    public static final JpaOperator SUM = new JpaOperator() {
        @Override
        public Expression<Number> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.sum(cast(expressions.get(0)));
        }
    };

    @NotNull
    private static Function<Expression<Boolean>, Predicate> mapToPredicate(CriteriaBuilder cb) {
        return expression -> {
            if (expression instanceof Predicate) {
                return (Predicate) expression;
            }
            return cb.isTrue(expression);
        };
    }

    public abstract Expression<?> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions);

    public static <T> Expression<T> cast(Expression<?> expression) {
        //noinspection unchecked
        return (Expression<T>) expression;
    }

    public static JpaOperator of(Operator operator) {
        switch (operator) {
            case NOT:
                return NOT;
            case AND:
                return AND;
            case OR:
                return OR;
            case GT:
                return GT;
            case EQ:
                return EQ;
            case NE:
                return NE;
            case GE:
                return GE;
            case LT:
                return LT;
            case LE:
                return LE;
            case LIKE:
                return LIKE;
            case ISNULL:
                return ISNULL;
            case IN:
                return IN;
            case BETWEEN:
                return BETWEEN;
            case LOWER:
                return LOWER;
            case UPPER:
                return UPPER;
            case SUBSTRING:
                return SUBSTRING;
            case TRIM:
                return TRIM;
            case LENGTH:
                return LENGTH;
            case ADD:
                return ADD;
            case SUBTRACT:
                return SUBTRACT;
            case MULTIPLY:
                return MULTIPLY;
            case DIVIDE:
                return DIVIDE;
            case MOD:
                return MOD;
            case NULLIF:
                return NULLIF;
            case IF_NULL:
                return IF_NULL;
            case MIN:
                return MIN;
            case MAX:
                return MAX;
            case COUNT:
                return COUNT;
            case AVG:
                return AVG;
            case SUM:
                return SUM;
            default:
                throw new UnsupportedOperationException(operator.name());
        }

    }
}