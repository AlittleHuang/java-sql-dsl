package github.sql.dsl;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class JdbcTest extends JpaTest {

    static {
        initByJdbc();
    }


}
