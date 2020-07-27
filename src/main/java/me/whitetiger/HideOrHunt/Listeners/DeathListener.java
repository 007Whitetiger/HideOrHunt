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
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    public HideOrHunt plugin;

    public DeathListener(HideOrHunt plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        HOHPlayer hohPlayer = plugin.getManager().getPlayer(p);
        if (!(hohPlayer == null)) {
            if (!hohPlayer.isFinal()) {
                Block anchor = hohPlayer.getAnchor();
                RespawnAnchor anchorData = (RespawnAnchor) anchor.getBlockData();
                if (anchorData.getCharges() == 0) {
                    hohPlayer.finalKill();
                    e.setDeathSound(Sound.ENTITY_ENDER_DRAGON_GROWL);
                    e.setDeathSoundVolume(100);
                }
            } else {
                hohPlayer.finalKill();
                Entity attacker = p.getKiller();
                if (!(attacker == null)) {
                    p.sendMessage(Utils.chat(Constants.prefix + "&cYou were final killed by " + attacker.getName() + "!\nYou're now out of the game!"));
                } else {
                    p.sendMessage(Utils.chat(Constants.prefix + "&cYou were killed!\nYou're now out of the game!"));
                }
                e.setDeathSound(Sound.ENTITY_ENDER_DRAGON_GROWL);
                e.setDeathSoundVolume(100);
            }
        }
    }
}
