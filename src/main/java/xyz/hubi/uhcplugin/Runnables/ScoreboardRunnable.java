package xyz.hubi.uhcplugin.Runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.hubi.uhcplugin.Services.SidebarGameService;
import xyz.hubi.uhcplugin.Services.SidebarService;

public class ScoreboardRunnable implements Runnable{

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getWorld().getName().equals("world")) {
                player.setScoreboard(SidebarService.getSidebar(player));
            } else {
                player.setScoreboard(SidebarGameService.getSidebar(player));
            }
        }
    }
}
