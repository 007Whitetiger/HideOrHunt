/* ---------------------------------------------------------------------------------------------------------------------
Copyright (c) 2020 007Whitetiger (Stijn Te Baerts) -- developer.whitetiger@gmail.com
This file and all other files associated with this file are owned by me (Stijn Te Baerts).
Please create your own code or ask me for permission at the email above
--------------------------------------------------------------------------------------------------------------------- */
package me.whitetiger.HideOrHunt.Utils;

import me.whitetiger.HideOrHunt.HideOrHunt;
import org.bukkit.Bukkit;

import java.util.logging.Level;

public class GameUtils {
    public static void typeWarn() {
        HideOrHunt.INSTANCE.getLogger().log(Level.SEVERE, "You messed up type in config!!\n The plugin will be disabled! \nPlease fix the config file and restart the server!");
        Bukkit.getPluginManager().disablePlugin(HideOrHunt.INSTANCE);
    }
}
