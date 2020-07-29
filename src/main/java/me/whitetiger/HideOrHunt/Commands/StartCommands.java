/* ---------------------------------------------------------------------------------------------------------------------
Copyright (c) 2020 007Whitetiger (Stijn Te Baerts) -- developer.whitetiger@gmail.com
This file and all other files associated with this file are owned by me (Stijn Te Baerts).
Please create your own code or ask me for permission at the email above
--------------------------------------------------------------------------------------------------------------------- */
package me.whitetiger.HideOrHunt.Commands;

import com.destroystokyo.paper.Title;
import me.whitetiger.HideOrHunt.Constants;
import me.whitetiger.HideOrHunt.Game.GameManager;
import me.whitetiger.HideOrHunt.HideOrHunt;
import me.whitetiger.HideOrHunt.Utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommands {
    public HideOrHunt plugin = HideOrHunt.INSTANCE;
    public GameManager gameManager = plugin.getGameManager();

    public void restart(CommandSender sender) {
        if (!sender.hasPermission(Constants.adminPerms)) return;

        gameManager.getPlayers().keySet().forEach(player -> {
            Title restartTitle = new Title(ChatUtils.chat("&cRestarted!"), ChatUtils.chat("The Game was restarted by " + sender.getName()));
            player.sendTitle(restartTitle);
            player.sendMessage(ChatUtils.chat(Constants.prefix + "Place a new beacon to join the new game!"));

        });

        gameManager.stop();

        plugin.manager = new GameManager();
    }
    public void setup(CommandSender sender) {
        World finalWorld = null;
        for (World world : plugin.getServer().getWorlds()) {
            if (world.getEnvironment() == World.Environment.NETHER) finalWorld = world;
        }
        if (finalWorld == null) {
            sender.sendMessage(ChatUtils.chat(Constants.prefix + "&cThere is no nether world in your server!"));
            return;
        }
        Location teleportLocation = new Location(finalWorld, 0, 100, 0);
        while (teleportLocation.getBlock().getType() == Material.AIR) {
            teleportLocation.setY(teleportLocation.getY() - 1);
        }
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.teleport(teleportLocation);
        });
    }
}
