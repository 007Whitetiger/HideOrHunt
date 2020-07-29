/* ---------------------------------------------------------------------------------------------------------------------
Copyright (c) 2020 007Whitetiger (Stijn Te Baerts) -- developer.whitetiger@gmail.com
This file and all other files associated with this file are owned by me (Stijn Te Baerts).
Please create your own code or ask me for permission at the email above
--------------------------------------------------------------------------------------------------------------------- */
package me.whitetiger.HideOrHunt;

import me.whitetiger.HideOrHunt.Commands.Freeze;
import me.whitetiger.HideOrHunt.Commands.StartCommands;
import me.whitetiger.HideOrHunt.Commands.StopCommands;
import me.whitetiger.HideOrHunt.HideOrHunt;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class CommandHandler implements CommandExecutor {
    public HideOrHunt plugin;
    public StopCommands stopCommands;
    public StartCommands startCommands;
    public Freeze pauseCommands;

    public CommandHandler(HideOrHunt plugin) {
        this.plugin = plugin;
        this.stopCommands = new StopCommands();
        this.startCommands = new StartCommands();
        this.pauseCommands = new Freeze();
        Objects.requireNonNull(plugin.getCommand("hideorhunt")).setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args[0].toLowerCase()) {
            case "pause":
            case "freeze":
                pauseCommands.freeze(sender);
                break;
            case "continue":
            case "unfreeze":
                pauseCommands.unFreeze(sender);
                break;
            case "remove":
                remove(sender, args);
                break;
            case "kill":
                kill(sender, args);
                break;
            case "restart":
                startCommands.restart(sender);
                break;
            default:
                sender.sendMessage("Not DONE");
                break;
        }
        return true;
    }

    public void remove(CommandSender sender, String[] args) {
        if (args.length == 1) {
            stopCommands.remove((Player) sender);
        } else {
            stopCommands.remove(Bukkit.getPlayer(args[1]));
        }
    }
    public void kill(CommandSender sender, String[] args) {
        if (args.length == 1) {
            stopCommands.kill((Player) sender);
        } else {
            stopCommands.kill(Bukkit.getPlayer(args[1]));
        }
    }
}
