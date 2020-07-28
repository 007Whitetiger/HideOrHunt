/* ---------------------------------------------------------------------------------------------------------------------
Copyright (c) 2020 007Whitetiger (Stijn Te Baerts) -- developer.whitetiger@gmail.com
This file and all other files associated with this file are owned by me (Stijn Te Baerts).
Please create your own code or ask me for permission at the email above
--------------------------------------------------------------------------------------------------------------------- */
package me.whitetiger.HideOrHunt.Listeners;

import me.whitetiger.HideOrHunt.Constants;
import me.whitetiger.HideOrHunt.Game.GameManager;
import me.whitetiger.HideOrHunt.Game.HOHPlayer;
import me.whitetiger.HideOrHunt.HideOrHunt;
import me.whitetiger.HideOrHunt.Utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {
    public HideOrHunt plugin;
    public GameManager manager;

    public RespawnListener(HideOrHunt plugin) {
        this.plugin = plugin;
        this.manager = plugin.getGameManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        HOHPlayer hohPlayer = manager.getPlayer(p);
        if (hohPlayer == null) return;

        if (!hohPlayer.isFinal()) {
            if (!e.isAnchorSpawn()) {
                e.setRespawnLocation(hohPlayer.getAnchorLocation());
                Block anchor = hohPlayer.getAnchor();
                RespawnAnchor anchorData = (RespawnAnchor) anchor.getBlockData();
                anchorData.setCharges(anchorData.getCharges() - 1);
                anchor.setBlockData(anchorData);
                p.sendMessage(ChatUtils.chat(Constants.prefix + "&cPlease click the respawn anchor!"));
            }
        }
    }
}
