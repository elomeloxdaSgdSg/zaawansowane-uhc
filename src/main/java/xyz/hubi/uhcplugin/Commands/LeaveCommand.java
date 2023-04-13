package xyz.hubi.uhcplugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.hubi.uhcplugin.data.UserManager;

public class LeaveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            UserManager.inGame.remove(sender.getName());
            World world = Bukkit.getWorld("world");
            Location spawn = new Location(world, 0, 100, 0);
            ((Player) sender).teleport(spawn);
        }
        return true;
    }
}
