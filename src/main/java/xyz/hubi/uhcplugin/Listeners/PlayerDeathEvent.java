package xyz.hubi.uhcplugin.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import xyz.hubi.uhcplugin.Database.DatabaseModel;
import xyz.hubi.uhcplugin.data.User;
import xyz.hubi.uhcplugin.data.UserManager;

import java.util.Optional;

public class PlayerDeathEvent implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Player player = (Player) event.getEntity();
        Player player2 = (Player) event.getEntity().getKiller();

        Optional<User> entity = UserManager.getUserByUUID(player.getUniqueId()).findFirst();
        Optional<User> killer = UserManager.getUserByUUID(player2.getUniqueId()).findFirst();

        User entit = entity.get();
        User kille = killer.get();
if (event.getEntity() instanceof Player) {
        entit.setDeaths(entit.getDeaths() - 1);
        kille.setKills(kille.getKills() + 1);
        entit.setGames(entit.getGames() + 1);
        DatabaseModel.getInstance().save();
        if (player2.getLocation().getWorld().getPlayers().size() == 2) {
            kille.setWins(kille.getWins() + 1);
            DatabaseModel.getInstance().save();
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getLocation().getWorld().getName().equals("Gierka")) {
                    World world = Bukkit.getWorld("world");
                    Location swiat = new Location(world, 0, 100, 0);
                    p.teleport(swiat);
                    UserManager.removeFromGame(p);
                }
            }
            }
        } else {
    System.out.println("a");
        }

    }
}
