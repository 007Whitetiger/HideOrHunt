/* ---------------------------------------------------------------------------------------------------------------------
Copyright (c) 2020 007Whitetiger (Stijn Te Baerts) -- developer.whitetiger@gmail.com
This file and all other files associated with this file are owned by me (Stijn Te Baerts).
Please create your own code or ask me for permission at the email above
--------------------------------------------------------------------------------------------------------------------- */
package me.whitetiger.HideOrHunt.Commands;

import me.whitetiger.HideOrHunt.Constants;
import me.whitetiger.HideOrHunt.Game.GameManager;
import me.whitetiger.HideOrHunt.Game.GameState;
import me.whitetiger.HideOrHunt.HideOrHunt;
import me.whitetiger.HideOrHunt.Utils.ChatUtils;
import org.bukkit.command.CommandSender;

public class Freeze {
    HideOrHunt plugin = HideOrHunt.INSTANCE;
    GameManager gameManager = plugin.getGameManager();

    public void freeze(CommandSender sender) {
        if (sender.hasPermission(Constants.adminPerms)) {
            gameManager.setGameState(GameState.PAUSED);
            plugin.getServer().broadcastMessage(ChatUtils.chat(Constants.prefix + "The game has been paused!"));
        }
    }
    public void unFreeze(CommandSender sender) {
        if (sender.hasPermission(Constants.adminPerms)) {
            gameManager.setGameState(GameState.ACTIVE);
            plugin.getServer().broadcastMessage(ChatUtils.chat(Constants.prefix + "The game continues!"));
        }
    }
}
