package github.sql.dsl.projection;

import java.util.HashMap;
import java.util.Map;

public interface UserInterface {

    static Map<String, Object> asMap(UserInterface userInterface) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", userInterface.getId());
        map.put("randomNumber", userInterface.getRandomNumber());
        map.put("username", userInterface.getUsername());
        map.put("pid", userInterface.getPid());
        map.put("valid", userInterface.isValid());
        return map;
    }

    int getId();

    int getRandomNumber();

    String getUsername();

    Integer getPid();

    boolean isValid();
}
