/* ---------------------------------------------------------------------------------------------------------------------
Copyright (c) 2020 007Whitetiger (Stijn Te Baerts) -- developer.whitetiger@gmail.com
This file and all other files associated with this file are owned by me (Stijn Te Baerts).
Please create your own code or ask me for permission at the email above
--------------------------------------------------------------------------------------------------------------------- */
package me.whitetiger.HideOrHunt.Game;

import org.bukkit.entity.Player;

public class HOHPlayer {

    public Player bukkitPlayer;
    public Boolean teamAlive;

    public HOHPlayer(Player p) {
        this.bukkitPlayer = p;
        this.teamAlive = false;
    }

    public void setTeamAlive(Boolean teamAlive) {
        this.teamAlive = teamAlive;
    }

    public Boolean getTeamAlive() {
        return teamAlive;
    }

    public Player getBukkitPlayer() {
        return bukkitPlayer;
    }

}
