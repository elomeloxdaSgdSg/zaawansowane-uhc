package xyz.hubi.uhcplugin.Runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.hubi.uhcplugin.Services.SidebarService;
import xyz.hubi.uhcplugin.UhcPlugin;
import xyz.hubi.uhcplugin.Utils.ChatHelper;
import xyz.hubi.uhcplugin.data.GameManager;

public class GameTimeRunnable implements Runnable {
    public static int czasgry = 0;

    @Override
    public void run() {
        czasgry++;
        if (ChatHelper.convert(czasgry).equals("0h 0m 30s")) {
            Bukkit.broadcastMessage("KUTASY");
        }

    }
}
