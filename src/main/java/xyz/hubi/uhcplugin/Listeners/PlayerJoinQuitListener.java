package xyz.hubi.uhcplugin.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.hubi.uhcplugin.Database.DatabaseModel;
import xyz.hubi.uhcplugin.Database.DatabaseUser;
import xyz.hubi.uhcplugin.data.User;
import xyz.hubi.uhcplugin.data.UserManager;

import java.util.Optional;

public class PlayerJoinQuitListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        World world = Bukkit.getWorld("world");
        Location location = new Location(world, 0, 100, 0);
        player.getInventory().clear();
        player.teleport(location);
        Optional<User> optionalUser = UserManager.getUserByUUID(player.getUniqueId()).findFirst();
        if (!optionalUser.isPresent()) {
            User user1 = UserManager.createUser(player);
            UserManager.addOnlineUser(user1);
            return;
        }
        User user = optionalUser.get();
        UserManager.addOnlineUser(user);

    }



    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Player player = event.getPlayer();
        Optional<User> optionalUser = UserManager.getUserByUUID(player.getUniqueId()).findFirst();
        if (!optionalUser.isPresent()) {
            Bukkit.getLogger().severe("Unable to retrieve USER from player: " + player.getName() + " while leaving");
            return;
        }
        User user = optionalUser.get();
        UserManager.removeOnlineUser(player.getUniqueId());
        DatabaseUser.saveUser(user);
    }
}
