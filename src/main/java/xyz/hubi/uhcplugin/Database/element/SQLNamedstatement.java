package xyz.hubi.uhcplugin.Database.element;

import org.bukkit.Bukkit;
import xyz.hubi.uhcplugin.Database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SQLNamedstatement {

    private final Map<String, Object> placeholders = new HashMap<>();

    private final HashMap<String, Integer> keyMapIndex;

    private final String sql;

    public SQLNamedstatement(String sql, Map<String, Integer> keyMap) {
        this.sql = sql;
        this.keyMapIndex = new HashMap<>(keyMap);
    }

    public void set(String key, Object value) {
        if (!this.keyMapIndex.containsKey(key))
            return;
        this.placeholders.put(key, value);
    }

    public void executeUpdate() {
        try {
            Connection con = Database.getConnection();
            try {
                PreparedStatement statement = setPlaceholders(con.prepareStatement(this.sql));
                try {
                    statement.executeUpdate();
                    if (statement != null)
                        statement.close();
                } catch (Throwable throwable) {
                    if (statement != null)
                        try {
                            statement.close();
                        } catch (Throwable throwable1) {
                            throwable.addSuppressed(throwable1);
                        }
                    throw throwable;
                }
                if (con != null)
                    con.close();
            } catch (Throwable throwable) {
                if (con != null)
                    try {
                        con.close();
                    } catch (Throwable throwable1) {
                        throwable.addSuppressed(throwable1);
                    }
                throw throwable;
            }
        } catch (SQLException sQLException) {}
    }

    public ResultSet executeQuery() {
        try {
            Connection con = Database.getConnection();
            try {
                PreparedStatement statement = setPlaceholders(con.prepareStatement(this.sql));
                try {
                    System.out.println(this.sql);
                    ResultSet resultSet = statement.executeQuery();
                    if (statement != null)
                        statement.close();
                    if (con != null)
                        con.close();
                    return resultSet;
                } catch (Throwable throwable) {
                    if (statement != null)
                        try {
                            statement.close();
                        } catch (Throwable throwable1) {
                            throwable.addSuppressed(throwable1);
                        }
                    throw throwable;
                }
            } catch (Throwable throwable) {
                if (con != null)
                    try {
                        con.close();
                    } catch (Throwable throwable1) {
                        throwable.addSuppressed(throwable1);
                    }
                throw throwable;
            }
        } catch (SQLException sqlException) {
            Bukkit.getLogger().severe("Could not execute query" + sqlException);
            return null;
        }
    }

    private PreparedStatement setPlaceholders(PreparedStatement preparedStatement) throws SQLException {
        for (Map.Entry<String, Object> placeholder : this.placeholders.entrySet())
            preparedStatement.setObject(((Integer)this.keyMapIndex.get(placeholder.getKey())).intValue(), placeholder.getValue());
        return preparedStatement;
    }
}
