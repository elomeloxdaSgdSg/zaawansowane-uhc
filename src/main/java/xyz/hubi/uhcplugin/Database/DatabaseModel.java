package xyz.hubi.uhcplugin.Database;

import org.bukkit.Bukkit;
import xyz.hubi.uhcplugin.Database.element.SQLBasicUtils;
import xyz.hubi.uhcplugin.Database.element.SQLElement;
import xyz.hubi.uhcplugin.Database.element.SQLTable;
import xyz.hubi.uhcplugin.Database.element.SQLType;
import xyz.hubi.uhcplugin.data.User;
import xyz.hubi.uhcplugin.data.UserManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseModel {
    private static DatabaseModel instance;

    public static SQLTable users;

    public DatabaseModel() {
        instance = this;
        loadModels();
    }

    public static DatabaseModel getInstance() {
        if (instance != null)
            return instance;
        return new DatabaseModel();
    }

    public static void loadModels() {
        users = new SQLTable("uhc_plugin");
        users.add("user_uuid", SQLType.VARCHAR, 36, true);
        users.add("user_name", SQLType.TEXT, true);
        users.add("user_lvl", SQLType.BIGINT);
        users.add("user_kills", SQLType.BIGINT);
        users.add("user_deaths", SQLType.BIGINT);
        users.add("user_games", SQLType.BIGINT);

    }

    public void load() throws SQLException {
        createTableIfNotExists(users);
        loadUsers();
    }

    public void loadUsers() throws SQLException {
        String query = "SELECT * FROM `uhc_plugin`";
        ResultSet resultSet = Database.getConnection().prepareStatement(query).executeQuery();
        if (resultSet.isClosed()) {
            Bukkit.getLogger().severe("Registered " + UserManager.getUsers().size() + " users! In daily + nagroda plugin");
            return;
        }
        while (resultSet.next())
            try {
                User user = DatabaseUser.deserializeUser(resultSet);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        Bukkit.getLogger().severe("Registered " + UserManager.getUsers().size() + " users!");
    }

    public void save() {
        for (User user : UserManager.getUsers().values())
            DatabaseUser.saveUser(user);
    }

    public void createTableIfNotExists(SQLTable table) {
        SQLBasicUtils.getCreate(table).executeUpdate();
        for (SQLElement sqlElement : table.getSqlElements())
            SQLBasicUtils.getAlter(table, sqlElement).executeUpdate();
    }
}
