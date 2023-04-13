package xyz.hubi.uhcplugin.Database;

import com.zaxxer.hikari.HikariDataSource;
import xyz.hubi.uhcplugin.PluginConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static Database instance;

    private final HikariDataSource dataSource;

    public Database() {
        instance = this;
        this.dataSource = new HikariDataSource();
        this.dataSource.setJdbcUrl("jdbc:mysql://" + PluginConfig.DATABASE_HOST + ":" + PluginConfig.DATABASE_PORT + "/" + PluginConfig.DATABSE_NAME + "?useSSL=true");
        this.dataSource.setUsername(PluginConfig.DATABASE_USER);
        this.dataSource.setPassword(PluginConfig.DATABASE_PASSWORD);
    }

    public static Database getInstance() {
        if (instance == null)
            return new Database();
        return instance;
    }

    public HikariDataSource getDataSource() {
        return this.dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().getDataSource().getConnection();
    }

    public void shutdown() {
        this.dataSource.close();
    }
}
