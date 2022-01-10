import com.fasterxml.jackson.databind.json.JsonMapper;
import com.mysql.cj.jdbc.MysqlDataSource;
import github.sql.dsl.query.QueryBuilder;
import github.sql.dsl.query.api.Query;
import github.sql.dsl.query.api.expression.Predicate;
import github.sql.dsl.query.api.suport.DbSets;
import github.sql.dsl.query.api.suport.builder.component.AggregateFunction;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;

public class Main {
    static Query<User> userQuery;

    @SneakyThrows
    public static void main(String[] args) {
        JsonMapper jsonMapper = new JsonMapper();
        MysqlDataSource source = new MysqlDataSource();
        source.setUrl("jdbc:mysql:///xiaoxi");
        source.setUser("root");
        source.setPassword("root");

        QueryBuilder mysqlDbSet = DbSets.mysql(source);

        Query<User> query = mysqlDbSet.query(User.class);
        // from.where(User::getId)
        //         .eq(111)
        //         .Or(builder -> builder.get(User::getId).eq(44)
        //                 .or(User::getPid).eq(12)
        //                 .build()
        //                 .not()
        //         )
        //         .And(builder -> builder
        //                 .get(User::getUsername).eq("admin")
        //                 .orNot(User::getId)
        //                 .eq(128)
        //                 .build()
        //         )
        //         .getResultList();
        List<User> all = query.fetch(User::getParentUser).getResultList();
        for (User user : all) {
            System.out.println(user);
        }


        List<User> resultList = query
                .where(Predicate.get(User::getId).eq(10))
                .and(User::getId).eq(2)
                .or(User::getId).eq(3)
                .and(User::getId).eq(4)
                .andAppend(builder -> builder
                        .get(User::getId).eq(5)
                        .or(User::getId).eq(6)
                        .and(User::getId).eq(7)
                        .build()
                        .not()
                )
                .orAppend(builder -> builder
                        .get(User::getId).eq(11)
                        .or(User::getId).gt(12)
                        .build()
                )
                .and(Predicate.get(User::getId).eq(8).and(User::getUsername).eq("10").not())
                .getResultList();

        List<Object[]> objects = query
                .select(User::getId, AggregateFunction.SUM)
                .select(User::getId, AggregateFunction.AVG)
                .select(User::getId, AggregateFunction.COUNT)
                .getObjectsList();

        for (Object[] object : objects) {
            System.out.println(Arrays.toString(object));
        }


        List<UserProjection> resultList1 = query
                .orderBy(User::getId).asc()
                .projected(UserProjection.class)
                .getResultList();
        for (UserProjection userProjection : resultList1) {
            System.out.println(userProjection.equals(resultList1.get(0)));
            System.out.println(userProjection);
            System.out.println(userProjection.hashCode());
        }
        // List<User> resultList = from.getResultList();
        // System.out.println(resultList.size());
        //
        // WhereBuilder<User> queryById = from.where(User::getId).eq(10);
        // List<User> list = queryById
        //         .getResultList();
        //
        // System.out.println(list.size());
        //
        // List<User> admin = queryById
        //         .OR(builder ->
        //                 builder.and(User::getUsername).eq("admin")
        //                         .or(User::getId).eq(2)
        //                         .build()
        //                         .not()
        //         )
        //         // .or(User::getParentUser).map(User::getId).eq(1)
        //         .getResultList();
        //
        // admin.forEach(System.out::println);


    }

    public interface UserProjection {

        int getId();

        String getUsername();

        boolean test();

    }

    @Data
    public static class UserProjection2 {
        int id;

        String username;
    }


}
