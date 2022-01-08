import com.mysql.cj.jdbc.MysqlDataSource;
import github.sql.dsl.query.DbSet;
import github.sql.dsl.query.api.Query;
import github.sql.dsl.query.suport.DbSets;
import github.sql.dsl.query.suport.builder.component.AggregateFunction;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;

public class Main {
    static Query<User> userQuery;

    @SneakyThrows
    public static void main(String[] args) {

        MysqlDataSource source = new MysqlDataSource();
        source.setUrl("jdbc:mysql:///xiaoxi");
        source.setUser("root");
        source.setPassword("root");

        DbSet query = DbSets.mysql(source);

        Query<User> from = query.from(User.class);
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
        List<User> all = from.fetch(User::getParentUser).getResultList();
        for (User user : all) {
            System.out.println(user);
        }


        List<User> resultList = from
                .where(User::getId).eq(1)
                .and(User::getId).eq(2)
                .or(User::getId).eq(3)
                .and(User::getId).eq(4)
                .And(builder -> builder
                        .get(User::getId).eq(5)
                        .or(User::getId).eq(6)
                        .and(User::getId).eq(7)
                        .build()
                        .not()
                )
                .getResultList();

        List<Object[]> objects = from
                .select(User::getId, AggregateFunction.SUM)
                .select(User::getId,AggregateFunction.AVG)
                .select(User::getId,AggregateFunction.COUNT)
                .getObjectsList();

        for (Object[] object : objects) {
            System.out.println(Arrays.toString(object));
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


}
