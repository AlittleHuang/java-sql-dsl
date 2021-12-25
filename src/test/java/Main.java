import github.sql.dsl.query.api.Entity;
import github.sql.dsl.query.api.QueryFactory;
import lombok.Data;

public class Main {

    private static QueryFactory query;

    public static void main(String[] args) {

        Object res = query.from(User.class)
                .where()
                .and(User::getUserId).eq(100)
                .and(User::getUsername).eq("alittlehuang")
                .and(builder -> {
                    builder.and(User::getParent).to(User::getUserId).eq(10);
                })
                .orderBy(User::getUserId).desc()
                // .groupBy(Column.of(User::getParent).to(User::getUserId))
                // .select(Column.of(User::getParent).to(User::getUserId))
                .fetch(User::getParent)
                .single();

    }

    @Data
    public static class User implements Entity {

        int userId;

        String username;

        User parent;

    }


}
