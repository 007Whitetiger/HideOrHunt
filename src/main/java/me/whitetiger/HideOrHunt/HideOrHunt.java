package me.whitetiger.HideOrHunt;

import me.whitetiger.HideOrHunt.Commands.CommandHandler;
import me.whitetiger.HideOrHunt.Game.GameManager;
import me.whitetiger.HideOrHunt.Listeners.*;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class HideOrHunt extends JavaPlugin {

    public GameManager manager = new GameManager();
    public static HideOrHunt INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        registerEvents();
        new CommandHandler(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerEvents() {
        new BlockPlaceListener(this);
        new ChatListener(this);
        new BlockBreakListener(this);
        new AllPlayerEventListener(this);
        new DeathListener(this);
    }

    public GameManager getManager() {
        return manager;
    }
}
