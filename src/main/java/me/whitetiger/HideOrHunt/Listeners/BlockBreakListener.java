/* ---------------------------------------------------------------------------------------------------------------------
Copyright (c) 2020 007Whitetiger (Stijn Te Baerts) -- developer.whitetiger@gmail.com
This file and all other files associated with this file are owned by me (Stijn Te Baerts).
Please create your own code or ask me for permission at the email above
--------------------------------------------------------------------------------------------------------------------- */
package me.whitetiger.HideOrHunt.Listeners;

import com.destroystokyo.paper.Title;
import me.whitetiger.HideOrHunt.Game.GameManager;
import me.whitetiger.HideOrHunt.Game.HOHPlayer;
import me.whitetiger.HideOrHunt.GameState;
import me.whitetiger.HideOrHunt.HideOrHunt;
import me.whitetiger.HideOrHunt.Utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;

import java.util.Objects;

public class BlockBreakListener implements Listener {
    public HideOrHunt plugin;
    public GameManager gameManager;

    public BlockBreakListener(HideOrHunt plugin) {
        this.plugin = plugin;
        this.gameManager = plugin.getGameManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDamage(BlockDamageEvent e) {

        if (!Objects.requireNonNull(e.getBlock()).getType().equals(Material.RESPAWN_ANCHOR)) return;

        HOHPlayer hohPlayer = gameManager.getByAnchorLocation(e.getBlock());
        if (hohPlayer == null) return;

        if (hohPlayer.getBukkitPlayer().equals(e.getPlayer())) {
            Title title = new Title(ChatUtils.chat("&cDon't!"), ChatUtils.chat("&cYou can't break your own anchor!"), 0, 7, 1);
            e.getPlayer().sendTitle(title);
            e.setCancelled(true);
            return;
        }

        if (!e.getPlayer().getInventory().getItemInMainHand().toString().toLowerCase().contains("pickaxe")) return;

        e.setInstaBreak(true);
        hohPlayer.kill(e.getPlayer().getName());


    }
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (gameManager.waiting()) return;
        if (!Objects.requireNonNull(e.getBlock()).getType().equals(Material.RESPAWN_ANCHOR)) return;

        HOHPlayer hohPlayer = gameManager.getByAnchorLocation(e.getBlock());
        if (hohPlayer == null) return;

        if (hohPlayer.getBukkitPlayer().equals(e.getPlayer())) {
            Title title = new Title(ChatUtils.chat("&cDon't!"), ChatUtils.chat("&cYou can't break your own anchor!"), 0, 40, 10);
            e.getPlayer().sendTitle(title);
            e.setCancelled(true);
        }
    }
}
