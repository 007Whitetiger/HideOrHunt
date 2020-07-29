/* ---------------------------------------------------------------------------------------------------------------------
Copyright (c) 2020 007Whitetiger (Stijn Te Baerts) -- developer.whitetiger@gmail.com
This file and all other files associated with this file are owned by me (Stijn Te Baerts).
Please create your own code or ask me for permission at the email above
--------------------------------------------------------------------------------------------------------------------- */
package me.whitetiger.HideOrHunt.Commands;

import me.whitetiger.HideOrHunt.Constants;
import me.whitetiger.HideOrHunt.Game.GameManager;
import me.whitetiger.HideOrHunt.Game.HOHPlayer;
import me.whitetiger.HideOrHunt.HideOrHunt;
import me.whitetiger.HideOrHunt.Utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class StopCommands {
    public HideOrHunt plugin = HideOrHunt.INSTANCE;
    public GameManager gameManager = plugin.getGameManager();

    public void remove(Player p) {
        gameManager.removePlayer(p);
        p.sendMessage(ChatUtils.chat(Constants.prefix + "&aYou have been removed of the game!"));
    }
    public void kill(Player p) {
        HOHPlayer hohPlayer = gameManager.getPlayer(p);
        hohPlayer.kill(ChatUtils.chat("&4ADMIN"));
        hohPlayer.getAnchor().setType(Material.AIR);
    }
}
