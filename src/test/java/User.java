import github.sql.dsl.query.api.expression.path.Entity;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class User implements Entity {

    int id;

    String username;

    Date time;

    Integer pid;

    @ManyToOne
    @JoinColumn(name = "pid")
    User parentUser;

}
