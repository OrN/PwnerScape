package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.entity.player.Player;

public interface PlayerMageListener {
    /**
     * Called when you mage a Player
     */
    public void onPlayerMage(Player player, Player affectedPlayer, int spell);
}
