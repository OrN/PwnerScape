package com.pwns.server.plugins.listeners.executive;

import com.pwns.server.model.entity.player.Player;

public interface DuelExecutiveListener {
    /**
     * Return true if you wish to prevent a user from duelling
     */
    public boolean blockDuel(Player p, Player p2);
}
