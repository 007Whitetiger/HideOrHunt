/* ---------------------------------------------------------------------------------------------------------------------
Copyright (c) 2020 007Whitetiger (Stijn Te Baerts) -- developer.whitetiger@gmail.com
This file and all other files associated with this file are owned by me (Stijn Te Baerts).
Please create your own code or ask me for permission at the email above
--------------------------------------------------------------------------------------------------------------------- */
package me.whitetiger.HideOrHunt.Game;

import me.whitetiger.HideOrHunt.GameState;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GameManager {

    public HashMap<Player, HOHPlayer> players = new HashMap<>();
    public GameState gameState;

    public GameManager() { }

    public HashMap<Player, HOHPlayer> getPlayers() {
        return players;
    }

    public int teamAmount() {
        return players.size();
    }

    public void addPlayers(Server server) {
        for (Object pObject : server.getOnlinePlayers().toArray()) {
            Player p = (Player) pObject;
            this.addPlayer(p);
        }
        this.gameState = GameState.WAITING;
    }

    public void addPlayer(Player p) {
        HOHPlayer hohPlayer = new HOHPlayer(p);
        this.players.put(p, hohPlayer);
    }

    public Boolean inGame(Player p) {
        return players.containsKey(p);
    }
}
