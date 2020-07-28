package me.whitetiger.HideOrHunt;

import me.whitetiger.HideOrHunt.Game.GameManager;
import me.whitetiger.HideOrHunt.Game.HOHPlayer;
import me.whitetiger.HideOrHunt.Listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public final class HideOrHunt extends JavaPlugin {

    public GameManager manager;
    public static HideOrHunt INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.saveDefaultConfig();
        Configuration config = this.getConfig();
        if (this.getConfig().getBoolean("Save")) {

            if (this.getConfig().contains("SaveData")) {

                /* get SaveData Section */
                ConfigurationSection saveData = this.getConfig().getConfigurationSection("SaveData");

                /* create GameManager */

                this.manager = new GameManager();
                assert saveData != null;

                /* manager specific */

                manager.setGameState(GameState.valueOf(saveData.getString("gameState")));
                manager.setWinnable(saveData.getBoolean("isWinnable"));

                /* player Hashmap */

                HashMap<Player, HOHPlayer> players = new HashMap<>();
                ConfigurationSection playersSection = saveData.getConfigurationSection("players");

                for (String section : Objects.requireNonNull(playersSection).getKeys(false)) {

                    /* player loop */

                    ConfigurationSection playerSection = playersSection.getConfigurationSection(section);
                    assert playerSection != null;
                    Location anchorLoc = playerSection.getLocation("anchor");
                    assert anchorLoc != null;
                    Player p = Bukkit.getPlayer(UUID.fromString(Objects.requireNonNull(playerSection.getString("player"))));
                    HOHPlayer hohPlayer = new HOHPlayer(p, anchorLoc.getBlock());
                    hohPlayer.setTeamAlive(playerSection.getBoolean("teamAlive"));
                    hohPlayer.setTeamNumber(playersSection.getInt("number"));
                    hohPlayer.setId(UUID.fromString(section));
                    players.put(p, hohPlayer);
                }
                manager.setPlayers(players);
            } else {
                this.manager = new GameManager();
            }
        } else {
            this.manager = new GameManager();
        }
        registerEvents();
        new CommandHandler(this);
    }

    @Override
    public void onDisable() {
        if (this.getConfig().getBoolean("Save")) {

            /* create SaveData section */

            if (!this.getConfig().contains("SaveData")) {
                this.getConfig().createSection("SaveData");
                this.saveConfig();
            }
            ConfigurationSection saveData = this.getConfig().getConfigurationSection("SaveData");
            assert saveData != null;

            /* create player section */

            if (!saveData.contains("players")) {
                saveData.createSection("players");
                this.saveConfig();
            }
            ConfigurationSection players = saveData.getConfigurationSection("players");
            for (Player p : this.manager.getPlayers().keySet()) {
                /* all player specific things */
                assert players != null;;
                HOHPlayer hohPlayer = this.manager.getPlayer(p);
                ConfigurationSection playerSection = players.createSection(hohPlayer.getId().toString());
                playerSection.set("player", p.getUniqueId().toString());
                playerSection.set("anchor", hohPlayer.getAnchorLocation());
                playerSection.set("teamAlive", hohPlayer.getTeamAlive());
                playerSection.set("number", hohPlayer.getTeamNumber());

            }
            /* gamemanager specific things */
            saveData.set("gameState", this.manager.getGameState().name());
            saveData.set("isWinnable", this.manager.getWinnable());

            /* save Config */
            this.saveConfig();
        }
    }

    public void registerEvents() {
        new BlockPlaceListener(this);
        new ChatListener(this);
        new BlockBreakListener(this);
        new AllPlayerEventListener(this);
        new DeathListener(this);
    }

    public GameManager getGameManager() {
        return manager;
    }
}
