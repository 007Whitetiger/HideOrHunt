package me.whitetiger.HideOrHunt;

import me.whitetiger.HideOrHunt.Game.GameManager;
import me.whitetiger.HideOrHunt.Listeners.BlockBreakListener;
import me.whitetiger.HideOrHunt.Listeners.BlockPlaceListener;
import me.whitetiger.HideOrHunt.Listeners.ChatListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class HideOrHunt extends JavaPlugin {

    public GameManager manager = new GameManager();

    @Override
    public void onEnable() {
        registerEvents();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerEvents() {
        new BlockPlaceListener(this);
        new ChatListener(this);
        new BlockBreakListener(this);
    }

    public GameManager getManager() {
        return manager;
    }
}
