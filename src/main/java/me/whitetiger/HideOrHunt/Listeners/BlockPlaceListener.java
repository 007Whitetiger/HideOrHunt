/* ---------------------------------------------------------------------------------------------------------------------
Copyright (c) 2020 007Whitetiger (Stijn Te Baerts) -- developer.whitetiger@gmail.com
This file and all other files associated with this file are owned by me (Stijn Te Baerts).
Please create your own code or ask me for permission at the email above
--------------------------------------------------------------------------------------------------------------------- */
package me.whitetiger.HideOrHunt.Listeners;

import me.whitetiger.HideOrHunt.HideOrHunt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {
    public HideOrHunt plugin;

    public BlockPlaceListener(HideOrHunt plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (plugin.getManager().inGame(p)) {
            e.setCancelled(true);
            p.sendMessage(p.getDisplayName() + " &");
            return;
        }

        if (e.getBlock().getType().equals(Material.RESPAWN_ANCHOR)) {
            plugin.manager.addPlayer(p);
            p.setDisplayName(ChatColor.GOLD + "Team " + ChatColor.DARK_AQUA + plugin.manager.teamAmount() + " " + ChatColor.RESET + p.getDisplayName());
            p.sendMessage(p.getDisplayName() + " Team added!");
        }
    }
}
