/* ---------------------------------------------------------------------------------------------------------------------
Copyright (c) 2020 007Whitetiger (Stijn Te Baerts) -- developer.whitetiger@gmail.com
This file and all other files associated with this file are owned by me (Stijn Te Baerts).
Please create your own code or ask me for permission at the email above
--------------------------------------------------------------------------------------------------------------------- */
package me.whitetiger.HideOrHunt.Listeners;

import me.whitetiger.HideOrHunt.Constants;
import me.whitetiger.HideOrHunt.Game.GameManager;
import me.whitetiger.HideOrHunt.Game.GameState;
import me.whitetiger.HideOrHunt.HideOrHunt;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class AllPlayerEventListener implements Listener {
    public HideOrHunt plugin;
    public GameManager gameManager;

    public AllPlayerEventListener(HideOrHunt plugin) {
        this.plugin = plugin;
        this.gameManager = plugin.getGameManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public Boolean pauseCheck(Player p) {
        if (!p.hasPermission(Constants.adminPerms)) {
            return plugin.getGameManager().getGameState() == GameState.PAUSED & gameManager.inGame(p);
        } else return false;
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        if (pauseCheck(e.getPlayer())) e.setCancelled(true);
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (pauseCheck(e.getPlayer())) e.setCancelled(true);
    }
    @EventHandler
    public void onPlayerEatEvent(PlayerItemConsumeEvent e) {
        if (pauseCheck(e.getPlayer())) e.setCancelled(true);
    }
    @EventHandler
    public void onPlayerInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        if (pauseCheck((Player) e.getWhoClicked())) e.setCancelled(true);
    }
    @EventHandler
    public void onPlayerPlaceEvent(BlockPlaceEvent e) {
        if (pauseCheck(e.getPlayer())) e.setCancelled(true);
    }
    @EventHandler
    public void onPlayerBreakEvent(BlockDamageEvent e) {
        if (pauseCheck(e.getPlayer())) e.setCancelled(true);
    }
}
