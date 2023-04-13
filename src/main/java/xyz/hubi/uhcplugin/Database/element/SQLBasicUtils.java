package xyz.hubi.uhcplugin.Database.element;

import com.google.common.base.Joiner;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

public class SQLBasicUtils {

    public static SQLNamedstatement getInsert(SQLTable table) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(table.getNameGraveAccent());
        sb.append(" (");
        sb.append(Joiner.on(", ").join(table.getSqlElements().stream().map(SQLElement::getKeyGraveAccent).toArray()));
        sb.append(") VALUES (");
        sb.append(Joiner.on(", ").join(table.getSqlElements().stream().map(sql -> sql.getKey().replace(sql.getKey(), "?")).toArray()));
        sb.append(") ON DUPLICATE KEY UPDATE ");
        sb.append(Joiner.on(", ").join(table.getSqlElements().stream().map(SQLElement::getKeyValuesAssignment).toArray()));
        return new SQLNamedstatement(sb.toString(), table.getMapElementsKey());
    }

    public static SQLNamedstatement getSelect(SQLTable table, String... sqlElements) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(Joiner.on(", ").join(Collections.singleton(Boolean.valueOf(Arrays.<String>stream(sqlElements).anyMatch(sql -> Objects.equals(sql, table.getSQLElement(sql).getKeyGraveAccent()))))));
        sb.append(" FROM ");
        sb.append(table.getNameGraveAccent());
        return new SQLNamedstatement(sb.toString(), new HashMap<>());
    }

    public static SQLNamedstatement getSelectAll(SQLTable table) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(table.getNameGraveAccent());
        return new SQLNamedstatement(sb.toString(), new HashMap<>());
    }

    public static SQLNamedstatement getUpdate(SQLTable table, SQLElement element) {
        HashMap<String, Integer> keyMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(table.getNameGraveAccent());
        sb.append(" SET ");
        sb.append(element.getKeyGraveAccent());
        sb.append(" = ?");
        sb.append(" WHERE ");
        sb.append(table.getPrimaryKey().getKeyGraveAccent());
        sb.append(" = ?");
        keyMap.put(element.getKey(), Integer.valueOf(1));
        keyMap.put(table.getPrimaryKey().getKey(), Integer.valueOf(2));
        return new SQLNamedstatement(sb.toString(), keyMap);
    }

    public static SQLNamedstatement getCreate(SQLTable table) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS ");
        sb.append(table.getNameGraveAccent());
        sb.append(" (");
        sb.append(Joiner.on(", ").join(table.getSqlElements().stream().map(sqlElement -> {
            StringBuilder element = new StringBuilder();
            element.append(sqlElement.getKeyGraveAccent());
            element.append(" ");
            element.append(sqlElement.getType());
            if (sqlElement.isNotNull())
                element.append(" NOT NULL");
            return element.toString();
        }).toArray()));
        sb.append(", PRIMARY KEY (");
        sb.append(table.getPrimaryKey().getKey());
        sb.append("));");
        return new SQLNamedstatement(sb.toString(), new HashMap<>());
    }

    public static SQLNamedstatement getDelete(SQLTable table) {
        HashMap<String, Integer> keyMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(table.getNameGraveAccent());
        sb.append(" WHERE ");
        sb.append(table.getPrimaryKey().getKeyGraveAccent());
        sb.append(" = ?");
        keyMap.put(table.getPrimaryKey().getKey(), Integer.valueOf(1));
        return new SQLNamedstatement(sb.toString(), keyMap);
    }

    public static SQLNamedstatement getAlter(SQLTable table, SQLElement column) {
        StringBuilder sb = new StringBuilder();
        int index = table.getIndexElement(column.getKey());
        sb.append("ALTER TABLE ");
        sb.append(table.getNameGraveAccent());
        sb.append(" ADD COLUMN ");
        sb.append(column.getKeyGraveAccent());
        sb.append(" ");
        sb.append(column.getType());
        sb.append((index == 0) ? " FIRST" : (" AFTER " + ((SQLElement)table.getSqlElements().get(index - 1)).getKeyGraveAccent()));
        sb.append(";");
        return new SQLNamedstatement(sb.toString(), new HashMap<>());
    }
}
