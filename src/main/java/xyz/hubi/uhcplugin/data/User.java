package xyz.hubi.uhcplugin.data;

import java.util.UUID;

public class User {

    private final UUID uuid;

    private final String name;

    private int lvl;

    private int kills;

    private int deaths;
    private int games;

    private int exp;
    private int wins;
    private boolean dc;

    public User(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.lvl = 0;
        this.kills = 0;
        this.deaths = 0;
        this.games = 0;
        this.exp = 0;
        this.wins = 0;

    }

    public String getName() {
        return this.name;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public int getLvl() {
        return this.lvl;
    }

    public int getWins() {
        return this.wins;
    }
    public int getKills() {
        return this.kills;
    }

    public int getDeaths() {
        return this.deaths;
    }

    public int getExp() {
        return this.exp;
    }

    public int getGames() {
        return this.games;
    }



    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public void setDc(boolean dc) {
        this.dc = dc;
    }

    public boolean isDc() {
        return this.dc;
    }
}
