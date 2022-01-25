package github.sql.dsl;

import github.sql.dsl.criteria.query.QueryBuilder;
import github.sql.dsl.criteria.query.builder.Query;
import github.sql.dsl.criteria.query.support.builder.component.AggregateFunction;
import github.sql.dsl.entity.User;
import github.sql.dsl.internal.QueryBuilders;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
public class StandardTest {

    private static List<User> allUsers;
    private static Query<User> userQuery;

    @BeforeAll
    public static void initAll() {
        EntityManager manager = EntityManagers.getEntityManager();
        QueryBuilder queryBuilder = QueryBuilders.jpa(manager);
        allUsers = Users.getUsers();
        userQuery = queryBuilder.query(User.class);

        doInTransaction(() -> {
            manager.createQuery("update User set pid=null").executeUpdate();
            manager.createQuery("delete from User").executeUpdate();
            for (User user : allUsers) {
                manager.persist(user);
            }
        });

        manager.clear();
    }

    @Test
    public void testComparablePredicateTesterGt() {

        List<User> qgt80 = userQuery
                .where(User::getId).gt(80)
                .orderBy(User::getId).asc()
                .getResultList();
        List<User> fgt80 = allUsers.stream()
                .filter(it -> it.getId() > 80)
                .collect(Collectors.toList());
        assertEquals(qgt80, fgt80);

    }

    @Test
    public void testPredicateTesterEq() {
        int userId = 20;
        User user = userQuery.where(User::getId)
                .eq(userId)
                .fetch(User::getParentUser)
                .fetch(User::getParentUser)
                .getSingleResult();
        assertNotNull(user);
        assertEquals(user.getId(), userId);
        if (user.getPid() != null) {
            User parentUser = user.getParentUser();
            assertNotNull(parentUser);
            assertEquals(user.getPid(), parentUser.getId());
            assertEquals(parentUser, userQuery.where(User::getId).eq(parentUser.getId()).getOne());
        }

    }

    @Test
    public void testAggregateFunction() {
        Object[] aggregated = userQuery
                .select(User::getId, AggregateFunction.MIN)
                .select(User::getId, AggregateFunction.MAX)
                .select(User::getId, AggregateFunction.COUNT)
                .select(User::getId, AggregateFunction.AVG)
                .select(User::getId, AggregateFunction.SUM)
                .getOne();
        assertNotNull(aggregated);
        assertEquals(getUserIdStream().min().orElse(0), aggregated[0]);
        assertEquals(getUserIdStream().max().orElse(0), aggregated[1]);
        assertEquals(getUserIdStream().count(), aggregated[2]);
        assertEquals(getUserIdStream().average().orElse(0), aggregated[3]);
        assertEquals((long) getUserIdStream().sum(), aggregated[4]);
    }

    @Test
    public void testGroupBy() {
        List<Object[]> resultList = userQuery
                .groupBy(User::getId)
                .groupBy(Arrays.asList(User::getPid, User::isValid))
                .select(User::isValid)
                .select(User::getId)
                .select(User::getPid)
                .getResultList();

        List<Object[]> resultList2 = userQuery
                .groupBy(User::getId)
                .groupBy(Arrays.asList(User::getPid, User::isValid))
                .select(User::isValid)
                .select(Arrays.asList(User::getId, User::getPid))
                .getResultList();
        assertEquals(resultList.size(), resultList2.size());
        for (int i = 0; i < resultList.size(); i++) {
            assertArrayEquals(resultList.get(i), resultList2.get(i));
        }
    }

    @Test
    public void testOrderBy() {
        List<User> list = userQuery
                .orderBy(User::getId).desc()
                .getResultList();
        ArrayList<User> sorted = new ArrayList<>(allUsers);
        sorted.sort((a, b) -> Integer.compare(b.getId(), a.getId()));
        assertEquals(list, sorted);

        list = userQuery
                .orderBy(User::getPid).asc()
                .orderBy(User::getId).desc()
                .getResultList();

        sorted.sort((a, b) -> Integer.compare(b.getId(), a.getId()));
        sorted.sort(Comparator.comparingInt(user -> user.getPid() == null ? Integer.MIN_VALUE : user.getPid()));
        assertEquals(list, sorted);


    }

    @NotNull
    private IntStream getUserIdStream() {
        return allUsers.stream().mapToInt(User::getId);
    }

    public static void doInTransaction(Runnable action) {
        Object o = doInTransaction(() -> {
            action.run();
            return null;
        });
        log.trace("{}", o);
    }

    public static <T> T doInTransaction(Callable<T> action) {
        EntityManager manager = EntityManagers.getEntityManager();

        Session session = manager.unwrap(Session.class);
        Transaction transaction = session.getTransaction();
        T result;
        try {
            transaction.begin();
            result = action.call();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }

        return result;
    }

}
