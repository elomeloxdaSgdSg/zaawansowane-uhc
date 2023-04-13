package xyz.hubi.uhcplugin.Commands;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.FileUtil;
import xyz.hubi.uhcplugin.Runnables.GameTimeRunnable;
import xyz.hubi.uhcplugin.Services.SidebarService;
import xyz.hubi.uhcplugin.UhcPlugin;
import xyz.hubi.uhcplugin.Utils.ChatHelper;
import xyz.hubi.uhcplugin.data.GameManager;
import xyz.hubi.uhcplugin.data.UserManager;

import java.io.File;
import java.io.IOException;

public class EndGameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp()) {
                UhcPlugin.gra.put("gra", "false");
                UserManager.inGame.clear();
                Bukkit.broadcastMessage(ChatHelper.fix("&8[&a&l<X>&8] &7Gra zakonczona"));
                for (Player player2 : Bukkit.getOnlinePlayers()) {
                    if (player2.getWorld().getName().equals("Gierka")) {
                        World world = Bukkit.getWorld("world");
                        Location location = new Location(world, 0, 100, 0);
                        player2.teleport(location);
                    }
                }
                File file = new File(Bukkit.getWorld("Gierka").getWorldFolder().getPath());
                System.out.println(Bukkit.getWorld("Gierka").getWorldFolder().getPath());
                try {
                    FileUtils.deleteDirectory(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return true;
    }


}
