/* ---------------------------------------------------------------------------------------------------------------------
Copyright (c) 2020 007Whitetiger (Stijn Te Baerts) -- developer.whitetiger@gmail.com
This file and all other files associated with this file are owned by me (Stijn Te Baerts).
Please create your own code or ask me for permission at the email above
--------------------------------------------------------------------------------------------------------------------- */
package me.whitetiger.HideOrHunt.Game;

import com.destroystokyo.paper.Title;
import me.whitetiger.HideOrHunt.Constants;
import me.whitetiger.HideOrHunt.GameState;
import me.whitetiger.HideOrHunt.HideOrHunt;
import me.whitetiger.HideOrHunt.Utils.Utils;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;

import java.util.HashMap;

public class GameManager {

    public HashMap<Player, HOHPlayer> players = new HashMap<>();
    public GameState gameState;
    public HideOrHunt plugin = HideOrHunt.INSTANCE;
    private Boolean isWinnable = false;

    public GameManager() {
        this.gameState = GameState.ACTIVE;
    }

    public HashMap<Player, HOHPlayer> getPlayers() {
        return players;
    }

    public Boolean getWinnable() {
        return isWinnable;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int teamAmount() {
        return players.size();
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setPlayers(HashMap<Player, HOHPlayer> players) {
        this.players = players;
    }

    public void setWinnable(Boolean winnable) {
        isWinnable = winnable;
    }

    public void winCheck() {
        System.out.println(players.size());
        if (players.size() == 1 & isWinnable) {
            Player winner = (Player) players.keySet().toArray()[0];
            HOHPlayer hohWinner = players.get(winner);
            plugin.getServer().broadcastMessage(Utils.chat(Constants.prefix + winner.getDisplayName() + " &6from team &f" + hohWinner.getTeamNumber() + " &6has won!"));
            Title winnerTitle = new Title(Utils.chat("&6Winner!"), Utils.chat("The game has been won by " + winner.getDisplayName() + " from team " + hohWinner.getTeamNumber()), 20, 100, 20);
            plugin.getServer().getOnlinePlayers().forEach(player -> {
                player.sendTitle(winnerTitle);
            });
        }
    }

    public HOHPlayer getPlayer(Player p) {
        if (players.containsKey(p)) {
            return players.get(p);
        }
        return null;
    }

    public HOHPlayer getByAnchorLocation(Block anchor) {
        for (HOHPlayer player : players.values()) {
            if (anchor.getLocation().getX() == player.getAnchorLocation().getX() & anchor.getLocation().getY() == player.getAnchorLocation().getY() & anchor.getLocation().getZ() == player.getAnchorLocation().getZ()) {
                return player;
            }
        }
        return null;
    }


    public HOHPlayer addPlayer(Player p, Block anchor) {
        HOHPlayer hohPlayer = new HOHPlayer(p, anchor);
        this.players.put(p, hohPlayer);
        if (players.size() == 2) {
            this.isWinnable = true;
        }
        return hohPlayer;
    }

    public void removePlayer(Player p) {
        p.setPlayerListName(p.getDisplayName());
        HOHPlayer hohPlayer = getPlayer(p);
        Block anchor = hohPlayer.getAnchor();
        anchor.setType(Material.AIR);
        players.remove(p);
    }

    public Boolean inGame(Player p) {
        return players.containsKey(p);
    }

}
