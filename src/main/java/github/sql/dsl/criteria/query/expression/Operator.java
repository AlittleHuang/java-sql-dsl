package github.sql.dsl.criteria.query.expression;

import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public abstract class Operator {

    private static final AtomicInteger id = new AtomicInteger();

    public static final Operator NOT = new Operator("not", 10) {
        @Override
        public Predicate operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.not(cast(expressions.get(0)));
        }
    };

    public static final Operator AND = new Operator("and", 11) {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            Predicate[] restrictions = expressions.stream()
                    .map(Operator::<Boolean>cast)
                    .map(cb::isTrue)
                    .toArray(Predicate[]::new);
            return cb.and(restrictions);
        }
    };

    public static final Operator OR = new Operator("or", 13) {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            Predicate[] restrictions = expressions.stream()
                    .map(Operator::<Boolean>cast)
                    .map(cb::isTrue)
                    .toArray(Predicate[]::new);
            return cb.or(restrictions);
        }
    };

    public static final Operator GT = new Operator(">", 8) {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.greaterThan(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };

    public static final Operator EQ = new Operator("=", 8) {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.equal(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };

    public static final Operator NE = new Operator("<>", 8) {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.notEqual(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };

    public static final Operator GE = new Operator(">=", 8) {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.greaterThanOrEqualTo(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };

    public static final Operator LT = new Operator("<", 8) {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.lessThan(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };

    public static final Operator LE = new Operator("<=", 8) {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.lessThanOrEqualTo(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };

    public static final Operator LIKE = new Operator("like", 8) {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.like(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };

    public static final Operator ISNULL = new Operator("isnull", 0) {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.isNull(cast(expressions.get(0)));
        }
    };

    public static final Operator IN = new Operator("in", 0) {
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

    public static final Operator BETWEEN = new Operator("between", 8) {
        @Override
        public Expression<Boolean> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.between(cast(expressions.get(0)), cast(expressions.get(1)), cast(expressions.get(2)));
        }
    };

    public static final Operator LOWER = new Operator("lower", 0) {
        @Override
        public Expression<String> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.lower(cast(expressions.get(0)));
        }
    };

    public static final Operator UPPER = new Operator("upper", 0) {
        @Override
        public Expression<String> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.upper(cast(expressions.get(0)));
        }
    };

    public static final Operator SUBSTRING = new Operator("substring", 0) {
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

    public static final Operator TRIM = new Operator("trim", 0) {
        @Override
        public Expression<String> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.trim(cast(expressions.get(0)));
        }
    };

    public static final Operator LENGTH = new Operator("length", 0) {
        @Override
        public Expression<Integer> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.length(cast(expressions.get(0)));
        }
    };

    public static final Operator ADD = new Operator("+", 4) {
        @Override
        public Expression<Number> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.sum(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };

    public static final Operator SUBTRACT = new Operator("-", 4) {
        @Override
        public Expression<Number> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.diff(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };

    public static final Operator MULTIPLY = new Operator("*", 3) {
        @Override
        public Expression<Number> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.prod(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };

    public static final Operator DIVIDE = new Operator("/", 3) {
        @Override
        public Expression<Number> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.quot(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };

    public static final Operator MOD = new Operator("mod", 3) {
        @Override
        public Expression<Integer> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.mod(cast(expressions.get(0)), cast(expressions.get(1)));
        }
    };

    public static final Operator NULLIF = new Operator("nullif", 0) {
        @Override
        public Expression<Object> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cast(cb.nullif(expressions.get(0), expressions.get(1)));
        }
    };

    public static final Operator IF_NULL = new Operator("ifnull", 0) {
        @Override
        public Expression<Object> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cast(cb.coalesce(expressions.get(0), expressions.get(1)));
        }
    };


    public static final Operator MIN = new Operator("min", 0) {
        @Override
        public Expression<Number> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.min(cast(expressions.get(0)));
        }
    };

    public static final Operator MAX = new Operator("max", 0) {
        @Override
        public Expression<Number> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.max(cast(expressions.get(0)));
        }
    };

    public static final Operator COUNT = new Operator("count", 0) {
        @Override
        public Expression<Long> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.count(cast(expressions.get(0)));
        }
    };

    public static final Operator AVG = new Operator("avg", 0) {
        @Override
        public Expression<Double> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.avg(cast(expressions.get(0)));
        }
    };

    public static final Operator SUM = new Operator("sum", 0) {
        @Override
        public Expression<Number> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions) {
            return cb.sum(cast(expressions.get(0)));
        }
    };

    public final int index;
    public final String sign;
    public final int precedence;

    Operator(String sign, int priority) {
        this.sign = sign;
        this.precedence = priority;
        index = id.getAndIncrement();
    }

    public abstract Expression<?> operate(CriteriaBuilder cb, List<? extends Expression<?>> expressions);

    @Override
    public String toString() {
        return sign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operator operator = (Operator) o;
        return index == operator.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }

    public static <T> Expression<T> cast(Expression<?> expression) {
        //noinspection unchecked
        return (Expression<T>) expression;
    }


}
