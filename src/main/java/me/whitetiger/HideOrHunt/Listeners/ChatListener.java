/* ---------------------------------------------------------------------------------------------------------------------
Copyright (c) 2020 007Whitetiger (Stijn Te Baerts) -- developer.whitetiger@gmail.com
This file and all other files associated with this file are owned by me (Stijn Te Baerts).
Please create your own code or ask me for permission at the email above
--------------------------------------------------------------------------------------------------------------------- */
package me.whitetiger.HideOrHunt.Listeners;

import me.whitetiger.HideOrHunt.Game.HOHPlayer;
import me.whitetiger.HideOrHunt.HideOrHunt;
import me.whitetiger.HideOrHunt.Utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    public HideOrHunt plugin;


    public ChatListener(HideOrHunt plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChatMessage(AsyncPlayerChatEvent e) {
        HOHPlayer hohPlayer = plugin.getGameManager().getPlayer(e.getPlayer());
        if (!(hohPlayer == null)) {
            e.setFormat(ChatUtils.chat("&6Team &f") + hohPlayer.getTeamNumber() + " " + e.getPlayer().getDisplayName() + ChatColor.RESET + ": " + ChatUtils.chat(e.getMessage()));
        } else {
            e.setFormat(e.getPlayer().getDisplayName() + ChatColor.RESET + ": " + ChatUtils.chat(e.getMessage()));
        }
    }
}
