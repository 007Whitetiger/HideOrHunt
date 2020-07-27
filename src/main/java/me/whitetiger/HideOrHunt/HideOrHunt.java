package me.whitetiger.HideOrHunt;

import me.whitetiger.HideOrHunt.Game.GameManager;
import me.whitetiger.HideOrHunt.Listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class HideOrHunt extends JavaPlugin {

    public GameManager manager;
    public static HideOrHunt INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        this.manager = new GameManager();
        registerEvents();
        new CommandHandler(this);
    }

    @Override
    public void onDisable() {

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
