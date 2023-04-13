package xyz.hubi.uhcplugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.hubi.uhcplugin.UhcPlugin;
import xyz.hubi.uhcplugin.Utils.ChatHelper;
import xyz.hubi.uhcplugin.data.GameManager;
import xyz.hubi.uhcplugin.data.UserManager;

public class ArenaJoinCommand implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (UhcPlugin.gra.get("gra").equals("false")) {
            if (!(UserManager.inGame.equals(player.getName()))) {
                UserManager.addToGame(player);
                player.sendMessage(ChatHelper.fix("&8[&a&l<Y>&8] &7Dolaczyles do gry &6"));
                ChatHelper.sendActionBarToAll("&8>> &6" + sender.getName() + "&7 dolączył do gry!");
                System.out.println(UserManager.inGame);


            } else {
                player.sendMessage(ChatHelper.fix("&8[&c&l<X>&8] &7Jestes juz w poczekalni!"));

            }
        } else {
            player.sendMessage(ChatHelper.fix("&8[&c&l<X>&8] &7Gra jest w trakcie!"));

        }
        return true;
    }
}
