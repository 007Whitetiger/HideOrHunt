/* ---------------------------------------------------------------------------------------------------------------------
Copyright (c) 2020 007Whitetiger (Stijn Te Baerts) -- developer.whitetiger@gmail.com
This file and all other files associated with this file are owned by me (Stijn Te Baerts).
Please create your own code or ask me for permission at the email above
--------------------------------------------------------------------------------------------------------------------- */
package me.whitetiger.HideOrHunt.Listeners;

import com.destroystokyo.paper.Title;
import me.whitetiger.HideOrHunt.Game.HOHPlayer;
import me.whitetiger.HideOrHunt.HideOrHunt;
import me.whitetiger.HideOrHunt.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class BlockBreakListener implements Listener {
    public HideOrHunt plugin;

    public BlockBreakListener(HideOrHunt plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInteractEvent(BlockDamageEvent e) {

        if (!Objects.requireNonNull(e.getBlock()).getType().equals(Material.RESPAWN_ANCHOR)) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().toString().toLowerCase().contains("pickaxe")) return;


        HOHPlayer hohPlayer = plugin.getManager().getByAnchorLocation(e.getBlock());
        if (hohPlayer.getBukkitPlayer().equals(e.getPlayer())) {
            Title title = new Title(Utils.chat("&cYou can't destroy your own beacon!"));
            e.getPlayer().sendTitle(title);
            return;
        }
        if (!(hohPlayer == null)) {
            e.setInstaBreak(true);
            hohPlayer.kill(e.getPlayer());
        }

    }
}
