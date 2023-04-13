package xyz.hubi.uhcplugin;

public class PluginConfig {


    public static String DATABASE_HOST = UhcPlugin.getInstance().getConfig().getString("database-host");

    public static int DATABASE_PORT = UhcPlugin.getInstance().getConfig().getInt("database-port");

    public static String DATABASE_USER = UhcPlugin.getInstance().getConfig().getString("database-user");

    public static String DATABASE_PASSWORD = UhcPlugin.getInstance().getConfig().getString("database-password");

    public static String DATABSE_NAME = UhcPlugin.getInstance().getConfig().getString("database-name");


}
