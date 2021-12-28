package github.sql.dsl.query.api.column;

import github.sql.dsl.query.suport.common.model.AbstractExpression;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class PathExpression<T> extends AbstractExpression<T> {

    protected final List<String> path;

    private PathExpression(String... path) {
        this.path = Arrays.asList(path);
    }

    public PathExpression(List<String> path) {
        this.path = path;
    }

}
