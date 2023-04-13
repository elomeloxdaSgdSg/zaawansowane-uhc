package xyz.hubi.uhcplugin.data;

import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.hubi.uhcplugin.Runnables.GameTimeRunnable;
import xyz.hubi.uhcplugin.Runnables.ScoreboardRunnable;
import xyz.hubi.uhcplugin.UhcPlugin;
import xyz.hubi.uhcplugin.Utils.ChatHelper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class GameManager {






    public static int i = 30;

    public static void startGame() {
        i = 30;

        (new BukkitRunnable() {
            public void run() {
                i--;
                if (i > 0) {
                    Bukkit.broadcastMessage(ChatHelper.fix("&8>> &7Gra startuje za &6" + String.valueOf(i) + "&7 sekund! Dołącz za pomocą komendy &6/arenajoin"));

                }
                if (i == 0) {
                    UhcPlugin.gra.put("gra", "true");
                    Bukkit.broadcastMessage(ChatHelper.fix("&8[&a&l<Y>&8] &7Gra wystartowała"));
                    ChatHelper.createWorld("Gierka");
                    for (Player p : Bukkit.getOnlinePlayers()) {

                        if (UserManager.inGame.contains(p.getName())) {
                            GameTimeRunnable sidebarUpdateRunnable = new GameTimeRunnable();
                            UhcPlugin.getInstance().getServer().getScheduler().runTaskTimer(UhcPlugin.getInstance(), sidebarUpdateRunnable, 20, 20);
                            World world = Bukkit.getWorld("Gierka");
                            world.getWorldBorder().setCenter(0, 0);
                            world.getWorldBorder().setSize(1000);
                            Location location = new Location(world, getRandomNumber(1, 500), 10, getRandomNumber(1, 500));
                            p.teleport(location);
                            Location location1 = new Location(world, p.getLocation().getX(), p.getWorld().getHighestBlockYAt(p.getLocation()), p.getLocation().getZ());
                            p.teleport(location1);
                            p.getInventory().clear();
                            ChatHelper.giveItems(p, new ItemStack(Material.IRON_AXE), new ItemStack(Material.IRON_PICKAXE), new ItemStack(Material.IRON_SHOVEL), new ItemStack(Material.APPLE, 16));
                            UserManager.inGame.clear();
                            p.setGameMode(GameMode.SURVIVAL);
                            GameManager.i = 0;

                        } else {
                            System.out.println("kutas");
                        }
                    }

                }
            }
        }).runTaskTimer(UhcPlugin.getInstance(), 0L, 20L);
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void endGame() {
        UhcPlugin.gra.put("gra", "false");
        UserManager.inGame.clear();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getWorld().getName().equals("Gierka")) {
                World world = Bukkit.getWorld("Gierka");
                Location location = new Location(world, getRandomNumber(1, 500), 10, getRandomNumber(1, 500));
                p.teleport(location);
                File file = new File(Bukkit.getWorld("Gierka").getWorldFolder().getPath());
                System.out.println(Bukkit.getWorld("Gierka").getWorldFolder().getPath());
                try {
                    FileUtils.deleteDirectory(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        }

}
