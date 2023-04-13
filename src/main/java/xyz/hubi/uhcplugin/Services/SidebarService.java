package xyz.hubi.uhcplugin.Services;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import xyz.hubi.uhcplugin.Utils.ChatHelper;
import xyz.hubi.uhcplugin.data.User;
import xyz.hubi.uhcplugin.data.UserManager;

import java.util.Optional;

public final class SidebarService {

    private static final ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();

    public static Scoreboard getSidebar(Player player) {
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("uhc", "uhc", ChatHelper.fix("&6&lUHC"));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Optional<User> optionalUser = UserManager.getUserByUUID(player.getUniqueId()).findFirst();
        User user = optionalUser.get();
        objective.getScore(" ").setScore(11);
        objective.getScore(ChatHelper.fix(" &8» &7Nick: &f" + player.getName())).setScore(10);
        objective.getScore(ChatHelper.fix(" &7&l")).setScore(9);
        objective.getScore(ChatHelper.fix(" &7EXP: &f" + user.getExp() + "&8/&7")).setScore(8);
        objective.getScore(ChatHelper.fix("&7&l")).setScore(7);
        objective.getScore(ChatHelper.fix(" &8» &6&lStatystyki")).setScore(6);
        objective.getScore(ChatHelper.fix(" &8» &7Zabojstwa: &f" + user.getKills())).setScore(5);
        objective.getScore(ChatHelper.fix(" &8» &7Smierci: &f" + user.getDeaths())).setScore(4);
        objective.getScore(ChatHelper.fix(" &8» &7Lvl: &f" + user.getLvl())).setScore(3);
        objective.getScore(ChatHelper.fix(" &8» &7Rozegrane gry: &f" + user.getGames())).setScore(1);





        objective.getScore("  ").setScore(1);
        return scoreboard;
    }
}
