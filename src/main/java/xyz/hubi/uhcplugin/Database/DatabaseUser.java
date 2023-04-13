package xyz.hubi.uhcplugin.Database;

import org.bukkit.Bukkit;
import xyz.hubi.uhcplugin.Database.element.SQLBasicUtils;
import xyz.hubi.uhcplugin.Database.element.SQLDeserializer;
import xyz.hubi.uhcplugin.Database.element.SQLNamedstatement;
import xyz.hubi.uhcplugin.data.User;

import java.sql.ResultSet;

public class DatabaseUser {
    public static User deserializeUser(ResultSet rs) {
        if (rs == null)
            return null;
        try {
            String uuid = rs.getString("user_uuid");
            String name = rs.getString("user_name");
            Integer lvl = rs.getInt("user_lvl");
            Integer kills = rs.getInt("user_kills");
            Integer deaths = rs.getInt("user_deaths");
            Integer games = rs.getInt("user_games");

            Object[] values = new Object[4];
            values[0] = uuid;
            values[1] = name;
            values[2] = lvl;
            values[3] = kills;
            values[4] = deaths;
            values[5]= games;

            return SQLDeserializer.desrializeUser(values);
        } catch (Exception ex) {
            Bukkit.getLogger().severe("Could not deserialize user " + ex);
            return null;
        }
    }

    public static void saveUser(User user) {
        SQLNamedstatement users = SQLBasicUtils.getInsert(DatabaseModel.users);
        users.set("user_uuid", user.getUuid().toString());
        users.set("user_name", user.getName());
        users.set("user_lvl", user.getLvl());
        users.set("user_kills", user.getKills());
        users.set("user_deaths", user.getDeaths());
        users.set("user_games", user.getGames());
        users.executeUpdate();
    }
}
