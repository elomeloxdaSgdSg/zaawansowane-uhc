package xyz.hubi.uhcplugin.Database.element;

import xyz.hubi.uhcplugin.data.User;
import xyz.hubi.uhcplugin.data.UserManager;

import java.util.UUID;

public class SQLDeserializer {
    public static User desrializeUser(Object[] values) {
        User user = UserManager.createUser((String)values[1], UUID.fromString((String)values[0]));

        return user;
    }
}
