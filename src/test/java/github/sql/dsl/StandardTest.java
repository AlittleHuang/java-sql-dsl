package github.sql.dsl;

import github.sql.dsl.criteria.query.QueryBuilder;
import github.sql.dsl.criteria.query.builder.Query;
import github.sql.dsl.criteria.query.builder.combination.WhereAssembler;
import github.sql.dsl.criteria.query.expression.Predicate;
import github.sql.dsl.criteria.query.expression.path.attribute.Attribute;
import github.sql.dsl.criteria.query.support.builder.component.AggregateFunction;
import github.sql.dsl.entity.User;
import github.sql.dsl.internal.QueryBuilders;
import lombok.Lombok;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.*;
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
            manager.createQuery("update User set pid = null").executeUpdate();
            manager.createQuery("delete from User").executeUpdate();
            for (User user : allUsers) {
                manager.persist(user);
            }
        });

        manager.clear();
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
            throw Lombok.sneakyThrow(e);
        }

        return result;
    }

    @Test
    public void testComparablePredicateTesterGt() {

        List<User> qgt80 = userQuery
                .where(User::getRandomNumber).gt(80)
                .orderBy(User::getId).asc()
                .getResultList();
        List<User> fgt80 = allUsers.stream()
                .filter(it -> it.getRandomNumber() > 80)
                .collect(Collectors.toList());
        assertEquals(qgt80, fgt80);

    }

    @Test
    public void testPredicateTesterEq() {
        int userId = 20;
        User user = userQuery
                .where(User::getId).eq(userId)
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
                .select(User::getRandomNumber, AggregateFunction.MIN)
                .select(User::getRandomNumber, AggregateFunction.MAX)
                .select(User::getRandomNumber, AggregateFunction.COUNT)
                .select(User::getRandomNumber, AggregateFunction.AVG)
                .select(User::getRandomNumber, AggregateFunction.SUM)
                .getOne();
        assertNotNull(aggregated);
        assertEquals(getUserIdStream().min().orElse(0), aggregated[0]);
        assertEquals(getUserIdStream().max().orElse(0), aggregated[1]);
        assertEquals(getUserIdStream().count(), aggregated[2]);
        assertEquals(getUserIdStream().average().orElse(0), (Double) aggregated[3], 0.0001);
        assertEquals((long) getUserIdStream().sum(), aggregated[4]);
    }

    @Test
    public void testSelect() {
        List<Object[]> qList = userQuery
                .select(User::getRandomNumber)
                .select(User::getUsername)
                .getResultList();

        List<Object[]> fList = allUsers.stream()
                .map(it -> new Object[]{it.getRandomNumber(), it.getUsername()})
                .collect(Collectors.toList());

        assertEqualsArrayList(qList, fList);

    }

    @Test
    public void testGroupBy() {
        List<Object[]> resultList = userQuery
                .groupBy(User::getRandomNumber)
                .groupBy(Arrays.asList(User::getPid, User::isValid))
                .select(User::isValid)
                .select(User::getRandomNumber)
                .select(User::getPid)
                .getResultList();

        List<Object[]> resultList2 = userQuery
                .groupBy(User::getRandomNumber)
                .groupBy(Arrays.asList(User::getPid, User::isValid))
                .select(User::isValid)
                .select(Arrays.asList(User::getRandomNumber, User::getPid))
                .getResultList();
        assertEqualsArrayList(resultList, resultList2);
    }

    private void assertEqualsArrayList(List<Object[]> resultList, List<Object[]> resultList2) {
        assertEquals(resultList.size(), resultList2.size());
        for (int i = 0; i < resultList.size(); i++) {
            assertArrayEquals(resultList.get(i), resultList2.get(i));
        }
    }

    @Test
    public void testOrderBy() {
        List<User> list = userQuery
                .orderBy(User::getRandomNumber).desc()
                .getResultList();
        ArrayList<User> sorted = new ArrayList<>(allUsers);
        sorted.sort((a, b) -> Integer.compare(b.getRandomNumber(), a.getRandomNumber()));
        assertEquals(list, sorted);

        list = userQuery
                .orderBy(User::getUsername).asc()
                .orderBy(User::getRandomNumber).desc()
                .getResultList();

        sorted.sort((a, b) -> Integer.compare(b.getRandomNumber(), a.getRandomNumber()));
        sorted.sort(Comparator.comparing(User::getUsername));
        assertEquals(list, sorted);

        list = userQuery
                .orderBy(User::getTime).asc()
                .getResultList();
        sorted = new ArrayList<>(allUsers);
        sorted.sort(Comparator.comparing(User::getTime));
        assertEquals(list, sorted);
    }

    @Test
    public void testPredicateNot() {
        List<User> qList = userQuery.where(Predicate
                        .get(User::getRandomNumber).ge(10)
                        .or(User::getRandomNumber).lt(5)
                        .not()
                )
                .getResultList();
        List<User> fList = allUsers.stream()
                .filter(it -> !(it.getRandomNumber() >= 10 || it.getRandomNumber() < 5))
                .collect(Collectors.toList());


        assertEquals(qList, fList);

    }

    @Test
    public void testIsNull() {

        List<User> qList = userQuery.whereNot(User::getPid).isNull()
                .getResultList();

        List<User> fList = allUsers.stream()
                .filter(it -> it.getPid() != null)
                .collect(Collectors.toList());
        assertEquals(qList, fList);

        qList = userQuery.where(User::getPid).isNull()
                .getResultList();

        fList = allUsers.stream()
                .filter(it -> it.getPid() == null)
                .collect(Collectors.toList());
        assertEquals(qList, fList);

    }

    @Test
    public void testOperator() {

        WhereAssembler<User> isValid = userQuery.where(Predicate.get(User::isValid));
        List<User> qList = isValid
                .getResultList();
        List<User> validUsers = allUsers.stream().filter(User::isValid)
                .collect(Collectors.toList());
        List<User> fList = validUsers;
        assertEquals(qList, fList);

        qList = isValid.and(User::getRandomNumber).eq(2)
                .getResultList();
        fList = validUsers.stream().filter(user -> user.getRandomNumber() == 2)
                .collect(Collectors.toList());
        assertEquals(qList, fList);

        qList = isValid.and(User::getPid).ne(2)
                .getResultList();
        fList = validUsers.stream().filter(user -> user.getPid() != null && user.getPid() != 2)
                .collect(Collectors.toList());
        assertEquals(qList, fList);

        qList = isValid.and(User::getRandomNumber).in(1, 2, 3)
                .getResultList();
        List<User> qList2 = isValid.and(User::getRandomNumber).in(Arrays.asList(1, 2, 3))
                .getResultList();
        fList = validUsers.stream().filter(user -> Arrays.asList(1, 2, 3).contains(user.getRandomNumber()))
                .collect(Collectors.toList());
        assertEquals(qList, fList);
        assertEquals(qList2, fList);


        qList = isValid.and(User::getPid).isNull()
                .getResultList();
        fList = validUsers.stream().filter(user -> user.getPid() == null)
                .collect(Collectors.toList());
        assertEquals(qList, fList);

        qList = isValid.and(User::getPid).nullIf(4).eq(2)
                .getResultList();
        fList = validUsers.stream().filter(user -> {
                    Integer pid = user.getPid();
                    if (pid != null && pid == 4) {
                        pid = null;
                    }
                    return pid != null && pid == 2;
                })
                .collect(Collectors.toList());
        assertEquals(qList, fList);

        qList = isValid.and(User::getPid).ifNull(2).eq(2)
                .getResultList();
        fList = validUsers.stream().filter(user -> {
                    Integer pid = user.getPid();
                    if (pid == null) {
                        pid = 2;
                    }
                    return pid == 2;
                })
                .collect(Collectors.toList());
        assertEquals(qList, fList);


        qList = isValid.and(User::getRandomNumber).ge(10)
                .getResultList();
        fList = validUsers.stream().filter(user -> user.getRandomNumber() >= 10)
                .collect(Collectors.toList());
        assertEquals(qList, fList);

        qList = isValid.and(User::getRandomNumber).gt(10)
                .getResultList();
        fList = validUsers.stream().filter(user -> user.getRandomNumber() > 10)
                .collect(Collectors.toList());
        assertEquals(qList, fList);

        qList = isValid.and(User::getRandomNumber).le(10)
                .getResultList();
        fList = validUsers.stream().filter(user -> user.getRandomNumber() <= 10)
                .collect(Collectors.toList());
        assertEquals(qList, fList);


        qList = isValid.and(User::getRandomNumber).lt(10)
                .getResultList();
        fList = validUsers.stream().filter(user -> user.getRandomNumber() < 10)
                .collect(Collectors.toList());
        assertEquals(qList, fList);

        qList = isValid.and(User::getRandomNumber).between(10, 15)
                .getResultList();
        fList = validUsers.stream().filter(user -> user.getRandomNumber() >= 10 && user.getRandomNumber() <= 15)
                .collect(Collectors.toList());
        assertEquals(qList, fList);


        qList = isValid.and(User::getRandomNumber).ge(User::getPid)
                .getResultList();
        fList = validUsers.stream().filter(user -> user.getPid() != null && user.getRandomNumber() >= user.getPid())
                .collect(Collectors.toList());
        assertEquals(qList, fList);

        qList = isValid.and(User::getRandomNumber).gt(User::getPid)
                .getResultList();
        fList = validUsers.stream().filter(user -> user.getPid() != null && user.getRandomNumber() > user.getPid())
                .collect(Collectors.toList());
        assertEquals(qList, fList);

        qList = isValid.and(User::getRandomNumber).le(User::getPid)
                .getResultList();
        fList = validUsers.stream().filter(user -> user.getPid() != null && user.getRandomNumber() <= user.getPid())
                .collect(Collectors.toList());
        assertEquals(qList, fList);


        qList = isValid.and(User::getRandomNumber).lt(User::getPid)
                .getResultList();
        fList = validUsers.stream().filter(user -> user.getPid() != null && user.getRandomNumber() < user.getPid())
                .collect(Collectors.toList());
        assertEquals(qList, fList);

        qList = isValid.and(User::getRandomNumber)
                .between(User::getRandomNumber, User::getPid)
                .getResultList();
        fList = validUsers.stream()
                .filter(user -> user.getPid() != null && user.getRandomNumber() >= user.getRandomNumber() && user.getRandomNumber() <= user.getPid())
                .collect(Collectors.toList());
        assertEquals(qList, fList);

    }

    @Test
    public void testPredicateAssembler() {


        String username = "Jeremy Keynes";
        List<User> qList = userQuery.where(User::isValid).eq(true)
                .and(User::getParentUser).map(User::getUsername).eq(username)
                .getResultList();
        List<User> fList = allUsers.stream()
                .filter(user -> user.isValid()
                        && user.getParentUser() != null
                        && Objects.equals(user.getParentUser().getUsername(), username))
                .collect(Collectors.toList());

        assertEquals(qList, fList);

        Attribute<User, Number> getUsername = User::getRandomNumber;
        qList = userQuery.where(User::isValid).eq(true)
                .and(getUsername).eq(10)
                .getResultList();
        fList = allUsers.stream()
                .filter(user -> user.isValid()
                        && Objects.equals(user.getRandomNumber(), 10))
                .collect(Collectors.toList());

        assertEquals(qList, fList);

        qList = userQuery.where(User::isValid).eq(true)
                .or(getUsername).eq(10)
                .getResultList();
        fList = allUsers.stream()
                .filter(user -> user.isValid()
                        || Objects.equals(user.getRandomNumber(), 10))
                .collect(Collectors.toList());

        assertEquals(qList, fList);


        qList = userQuery.where(User::isValid).eq(true)
                .andNot(getUsername).eq(10)
                .getResultList();
        fList = allUsers.stream()
                .filter(user -> user.isValid()
                        && !Objects.equals(user.getRandomNumber(), 10))
                .collect(Collectors.toList());

        assertEquals(qList, fList);

        qList = userQuery.where(User::isValid).eq(true)
                .orNot(getUsername).eq(10)
                .getResultList();
        fList = allUsers.stream()
                .filter(user -> user.isValid()
                        || !Objects.equals(user.getRandomNumber(), 10))
                .collect(Collectors.toList());

        assertEquals(qList, fList);


        Date time = allUsers.get(20).getTime();

        qList = userQuery.where(User::isValid).eq(true)
                .or(User::getParentUser).map(User::getUsername).eq(username)
                .and(User::getTime).ge(time)
                .getResultList();

        List<User> jeremy_keynes = userQuery.where(User::isValid).eq(true)
                .or(User::getParentUser).map(User::getUsername).eq(username)
                .fetch(User::getParentUser)
                .and(User::getTime).ge(time)
                .getResultList();

        fList = allUsers.stream()
                .filter(user -> user.isValid()
                        || (user.getParentUser() != null
                        && Objects.equals(user.getParentUser().getUsername(), username)
                        && user.getTime().getTime() >= time.getTime()))
                .collect(Collectors.toList());

        assertEquals(qList, fList);
        assertEquals(qList, jeremy_keynes);


        qList = userQuery.where(User::isValid).eq(true)
                .andNot(User::getRandomNumber).eq(5)
                .getResultList();
        fList = allUsers.stream()
                .filter(user -> user.isValid()
                        && user.getRandomNumber() != 5)
                .collect(Collectors.toList());

        assertEquals(qList, fList);

        qList = userQuery.where(User::isValid).eq(true)
                .orNot(User::getRandomNumber).ne(5)
                .getResultList();
        fList = allUsers.stream()
                .filter(user -> user.isValid()
                        || user.getRandomNumber() == 5)
                .collect(Collectors.toList());

        assertEquals(qList, fList);

        qList = userQuery.whereNot(User::getRandomNumber).eq(6)
                .orNot(User::isValid).ne(false)
                .getResultList();
        fList = allUsers.stream()
                .filter(user -> user.getRandomNumber() != 6
                        || !user.isValid())
                .collect(Collectors.toList());

        assertEquals((qList), (fList));

        qList = userQuery.whereNot(User::getRandomNumber).eq(6)
                .and(User::getParentUser).map(User::isValid).eq(true)
                .getResultList();
        fList = allUsers.stream()
                .filter(user -> user.getRandomNumber() != 6
                        && (user.getParentUser() != null && user.getParentUser().isValid()))
                .collect(Collectors.toList());

        assertEquals((qList), (fList));

        qList = userQuery.whereNot(User::getRandomNumber).eq(6)
                .andNot(User::getParentUser).map(User::isValid).eq(true)
                .getResultList();
        fList = allUsers.stream()
                .filter(user -> user.getRandomNumber() != 6
                        && (user.getParentUser() != null && !user.getParentUser().isValid()))
                .collect(Collectors.toList());

        assertEquals((qList), (fList));

        qList = userQuery.whereNot(User::getRandomNumber).eq(6)
                .orNot(User::getParentUser).map(User::isValid).eq(true)
                .getResultList();
        fList = allUsers.stream()
                .filter(user -> user.getRandomNumber() != 6
                        || (user.getParentUser() != null && !user.getParentUser().isValid()))
                .collect(Collectors.toList());

        assertEquals((qList), (fList));

    }

    private List<Integer> ids(List<User> users) {
        return users.stream().map(User::getId).collect(Collectors.toList());
    }

    // ----
    @NotNull
    private IntStream getUserIdStream() {
        return allUsers.stream().mapToInt(User::getRandomNumber);
    }

}
