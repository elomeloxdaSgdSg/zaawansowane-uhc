package xyz.hubi.uhcplugin.Services;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import xyz.hubi.uhcplugin.Runnables.GameTimeRunnable;
import xyz.hubi.uhcplugin.Utils.ChatHelper;
import xyz.hubi.uhcplugin.data.User;
import xyz.hubi.uhcplugin.data.UserManager;

import java.util.Optional;

public class SidebarGameService {

    private static final ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();

    public static Scoreboard getSidebar(Player player) {
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("uhc", "uhc", ChatHelper.fix("&6&lGra"));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Optional<User> optionalUser = UserManager.getUserByUUID(player.getUniqueId()).findFirst();
        User user = optionalUser.get();
        objective.getScore(" ").setScore(11);
        objective.getScore(ChatHelper.fix(" &8» &7Nick: &f" + player.getName())).setScore(10);
        objective.getScore(ChatHelper.fix(" &8")).setScore(9);
        objective.getScore(ChatHelper.fix(" &8>> &7Żywych osób: &f" + player.getWorld().getPlayers().size())).setScore(8);
        objective.getScore(ChatHelper.fix(" &8>> &7Wielkosc borderu:&f " + player.getWorld().getWorldBorder().getSize())).setScore(7);
        objective.getScore(ChatHelper.fix(" &8>> &7Czas gry:&f " + ChatHelper.convert(GameTimeRunnable.czasgry))).setScore(6);






        objective.getScore("  ").setScore(5);
        return scoreboard;
    }
}
