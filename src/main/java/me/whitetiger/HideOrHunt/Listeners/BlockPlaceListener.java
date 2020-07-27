/* ---------------------------------------------------------------------------------------------------------------------
Copyright (c) 2020 007Whitetiger (Stijn Te Baerts) -- developer.whitetiger@gmail.com
This file and all other files associated with this file are owned by me (Stijn Te Baerts).
Please create your own code or ask me for permission at the email above
--------------------------------------------------------------------------------------------------------------------- */
package me.whitetiger.HideOrHunt.Listeners;

import me.whitetiger.HideOrHunt.Constants;
import me.whitetiger.HideOrHunt.Game.HOHPlayer;
import me.whitetiger.HideOrHunt.HideOrHunt;
import me.whitetiger.HideOrHunt.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.type.RespawnAnchor;
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


        if (e.getBlock().getType().equals(Material.RESPAWN_ANCHOR)) {
            if (!(p.getWorld().getEnvironment() == World.Environment.NETHER)) return;
            if (plugin.getManager().inGame(p)) {
                e.setCancelled(true);
                p.sendMessage(Constants.alreadyPlaced);
            } else {
                HOHPlayer hohPlayer = plugin.manager.addPlayer(p, e.getBlock());
                RespawnAnchor anchor = (RespawnAnchor) e.getBlock().getBlockData();
                anchor.setCharges(2);
                e.getBlock().setBlockData(anchor);
                p.setPlayerListName(ChatColor.GOLD + "Team " + ChatColor.RESET + hohPlayer.getTeamNumber() + " " + ChatColor.RESET + p.getDisplayName());
                p.sendMessage(p.getDisplayName() + " Team added!");
            }
        }
    }
}
