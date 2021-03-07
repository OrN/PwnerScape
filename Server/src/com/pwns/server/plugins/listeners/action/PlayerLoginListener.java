package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.entity.player.Player;

/**
 * Interface for handling player logins
 */
public interface PlayerLoginListener {
    /**
     * Called when player logins
     */
    public void onPlayerLogin(Player player);
}
