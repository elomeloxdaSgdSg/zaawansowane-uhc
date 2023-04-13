package xyz.hubi.uhcplugin.data;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class UserManager {

    private static final HashMap<UUID, User> users = new HashMap<>();

    private static final HashMap<UUID, User> onlineUsers = new HashMap<>();

    public static ArrayList<String> inGame = new ArrayList<String>();


    public static User createUser(Player player) {
        User user = new User(player.getUniqueId(), player.getName());
        users.put(player.getUniqueId(), user);
        return user;
    }

    public static User addToGame(Player player) {
        User user = new User(player.getUniqueId(), player.getName());
        inGame.add(player.getName());
        return user;
    }






    public static User removeFromGame(Player player) {
        User user = new User(player.getUniqueId(), player.getName());
        inGame.remove(player.getName());
        return user;
    }


    public static User createUser(String name, UUID uuid) {
        User user = new User(uuid, name);
        users.put(uuid, user);
        return user;
    }

    public static void removeUser(Player player) {
        users.remove(player.getUniqueId());
    }

    public static Stream<User> getUserByUUID(UUID uuid) {
        return users.values().stream().filter(user -> user.getUuid().equals(uuid));
    }

    public static Stream<User> getUserByNickname(String nickname) {
        return users.values().stream().filter(user -> user.getName().equals(nickname));
    }

    public static HashMap<UUID, User> getUsers() {
        return users;
    }

    public static void addOnlineUser(User user) {
        onlineUsers.put(user.getUuid(), user);
    }

    public static void removeOnlineUser(User user) {
        onlineUsers.remove(user.getUuid());
    }

    public static void removeOnlineUser(UUID user) {
        onlineUsers.remove(user);
    }

    public static HashMap<UUID, User> getOnlineUsers() {
        return onlineUsers;
    }

    public static List<User> getOnlineUsersList() {
        return new ArrayList<>(onlineUsers.values());
    }
}
