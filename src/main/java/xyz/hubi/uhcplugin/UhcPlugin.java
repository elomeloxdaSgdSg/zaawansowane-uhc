package xyz.hubi.uhcplugin;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hubi.uhcplugin.Commands.ArenaJoinCommand;
import xyz.hubi.uhcplugin.Commands.EndGameCommand;
import xyz.hubi.uhcplugin.Commands.LeaveCommand;
import xyz.hubi.uhcplugin.Commands.StartGameCommand;
import xyz.hubi.uhcplugin.Database.DatabaseModel;
import xyz.hubi.uhcplugin.Listeners.PlayerDeathEvent;
import xyz.hubi.uhcplugin.Listeners.PlayerJoinQuitListener;
import xyz.hubi.uhcplugin.Runnables.ScoreboardRunnable;
import xyz.hubi.uhcplugin.data.GameManager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.stream.Stream;

public final class UhcPlugin extends JavaPlugin {
    private static Plugin instance;
    public static HashMap<String, String> gra = new HashMap<>();

    private DatabaseModel databaseModel;
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        UhcPlugin.gra.put("gra", "false");
        try {
            this.databaseModel = new DatabaseModel();
            this.databaseModel.load();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Stream.of(
                new PlayerJoinQuitListener(),
                new PlayerDeathEvent()
        ).forEach(listener -> this.getServer().getPluginManager().registerEvents(listener, this));
        ScoreboardRunnable sidebarUpdateRunnable = new ScoreboardRunnable();
        this.getServer().getScheduler().runTaskTimer(this, sidebarUpdateRunnable, 20, 60);
        getCommand("arenajoin").setExecutor(new ArenaJoinCommand());
        getCommand("startgame").setExecutor(new StartGameCommand());
        getCommand("endgame").setExecutor(new EndGameCommand());
        getCommand("leave").setExecutor(new LeaveCommand());

    }

    @Override
    public void onDisable() {
        Bukkit.unloadWorld("Gierka", false);
        Bukkit.getWorld("Gierka").getWorldFolder().delete();
        if (this.databaseModel == null) {
            Bukkit.getLogger().severe("Plugin wyjebalo tak szybko DB sie nie zaladowalo");
        } else {
            this.databaseModel.save();
        }
    }
    public static Plugin getInstance() {
        return instance;
    }


}
