package xyz.hubi.uhcplugin.Database.element;

import java.util.ArrayList;
import java.util.HashMap;

public class SQLTable {
    private final ArrayList<SQLElement> sqlElements = new ArrayList<>();

    private final String name;

    private int idPrimaryKey = 0;

    public SQLTable(String name) {
        this.name = name;
    }

    public void add(String key, SQLType type) {
        this.sqlElements.add(new SQLElement(key, type, -1, false));
    }

    public void add(String key, SQLType type, int size) {
        this.sqlElements.add(new SQLElement(key, type, size, false));
    }

    public void add(String key, SQLType type, boolean notNull) {
        this.sqlElements.add(new SQLElement(key, type, -1, notNull));
    }

    public void add(String key, SQLType type, int size, boolean notNull) {
        this.sqlElements.add(new SQLElement(key, type, size, notNull));
    }

    public void setPrimaryKey(String key) {
        for (int i = 0; i < this.sqlElements.size(); i++) {
            if (((SQLElement)this.sqlElements.get(i)).getKey().equalsIgnoreCase(key)) {
                setPrimaryKey(i);
                return;
            }
        }
        setPrimaryKey(0);
    }

    public void setPrimaryKey(int idPrimaryKey) {
        this.idPrimaryKey = idPrimaryKey;
    }

    public SQLElement getPrimaryKey() {
        return this.sqlElements.get(this.idPrimaryKey);
    }

    public String getName() {
        return this.name;
    }

    public String getNameGraveAccent() {
        return "`" + this.name + "`";
    }

    public ArrayList<SQLElement> getSqlElements() {
        return this.sqlElements;
    }

    public int getIndexElement(String key) {
        for (int index = 0; index < this.sqlElements.size(); ) {
            if (!((SQLElement)this.sqlElements.get(index)).getKey().equalsIgnoreCase(key)) {
                index++;
                continue;
            }
            return index;
        }
        return -1;
    }

    public SQLElement getSQLElement(String key) {
        for (SQLElement element : this.sqlElements) {
            if (element.getKey().equalsIgnoreCase(key))
                return element;
        }
        return null;
    }

    public HashMap<String, Integer> getMapElementsKey() {
        HashMap<String, Integer> elementsMap = new HashMap<>();
        for (int i = 1; i < this.sqlElements.size() + 1; i++)
            elementsMap.put(((SQLElement)this.sqlElements.get(i - 1)).getKey(), Integer.valueOf(i));
        return elementsMap;
    }
}
