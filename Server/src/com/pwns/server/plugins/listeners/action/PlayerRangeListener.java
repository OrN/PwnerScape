package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.entity.player.Player;

public interface PlayerRangeListener {

    /**
     * Called when a player ranges another player
     */
    public void onPlayerRange(Player p, Player affectedMob);

}
