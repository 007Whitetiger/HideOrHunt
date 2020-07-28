/* ---------------------------------------------------------------------------------------------------------------------
Copyright (c) 2020 007Whitetiger (Stijn Te Baerts) -- developer.whitetiger@gmail.com
This file and all other files associated with this file are owned by me (Stijn Te Baerts).
Please create your own code or ask me for permission at the email above
--------------------------------------------------------------------------------------------------------------------- */
package me.whitetiger.HideOrHunt.Game;

import me.whitetiger.HideOrHunt.HideOrHunt;
import me.whitetiger.HideOrHunt.Utils.Utils;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HOHPlayer {

    public Player bukkitPlayer;
    public Block anchor;
    public Boolean teamAlive;
    public int teamNumber = HideOrHunt.INSTANCE.getGameManager().teamAmount() + 1;
    public UUID id;

    public HOHPlayer(Player p, Block anchor) {
        this.bukkitPlayer = p;
        this.anchor = anchor;
        this.teamAlive = true;
        this.id = UUID.randomUUID();
    }

    public void setTeamAlive(Boolean teamAlive) {
        this.teamAlive = teamAlive;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setAnchor(Block anchor) {
        this.anchor = anchor;
    }

    public void setBukkitPlayer(Player bukkitPlayer) {
        this.bukkitPlayer = bukkitPlayer;
    }

    public Boolean getTeamAlive() {
        return teamAlive;
    }

    public Player getBukkitPlayer() {
        return bukkitPlayer;
    }

    public Block getAnchor() {
        return anchor;
    }

    public Location getAnchorLocation() {
        return anchor.getLocation();
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public Boolean isFinal() {
        return !this.teamAlive;
    }

    public UUID getId() {
        return id;
    }

    public void kill(String name) {
        this.teamAlive = false;
        bukkitPlayer.sendTitle(Utils.chat("&6You DIED"), "You were killed by " + name);
    }
    public void finalKill() {
        GameManager gameManager = HideOrHunt.INSTANCE.getGameManager();
        gameManager.removePlayer(bukkitPlayer);
    }
}
