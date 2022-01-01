import com.mysql.cj.jdbc.MysqlDataSource;
import github.sql.dsl.query.api.DbSet;
import github.sql.dsl.query.api.EntityAttributeBridge;
import github.sql.dsl.query.suport.DbSets;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;

public class Main {


    @SneakyThrows
    public static void main(String[] args) {

        MysqlDataSource source = new MysqlDataSource();
        source.setUrl("jdbc:mysql:///xiaoxi");
        source.setUser("root");
        source.setPassword("root");

        DbSet query = DbSets.mysql(source);


        List<Object[]> res = query.from(User.class)
                .where(User::getId).eq(14)
                .orderBy(User::getPid).desc()
                .groupBy(User::getPid)
                .groupBy(User::getId)
                .select(User::getPid)
                .select(User::getId)
                .getObjectsList();
        for (Object[] re : res) {
            System.out.println(Arrays.toString(re));
        }

        List<User> users = query.from(User.class)
                // .fetch(User::getParentUser)
                .fetch(EntityAttributeBridge.of(User::getParentUser).get(User::getParentUser))
                .getResultList();
        users.forEach(System.out::println);

    }


}
