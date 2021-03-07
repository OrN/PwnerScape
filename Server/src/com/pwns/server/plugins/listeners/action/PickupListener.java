package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.entity.GroundItem;
import com.pwns.server.model.entity.player.Player;

public interface PickupListener {
    /**
     * Called when a user picks up an item
     */
    public void onPickup(Player p, GroundItem i);
}
