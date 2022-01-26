package github.sql.dsl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;


@Slf4j
public class JdbcTest extends BaseTest {

    @BeforeAll
    public static void doBeforeAll() {
        new JdbcTest().init();
    }

    @Override
    public void init() {
        initByJdbc();
    }

}
