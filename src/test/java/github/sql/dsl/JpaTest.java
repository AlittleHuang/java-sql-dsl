package github.sql.dsl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;


@Slf4j
public class JpaTest extends BaseTest {

    @BeforeAll
    public static void initAll() {
        new JpaTest().init();
    }

}