package com.pwns.server.plugins.listeners.executive;

import com.pwns.server.model.entity.GroundItem;
import com.pwns.server.model.entity.player.Player;

public interface PickupExecutiveListener {
    /**
     * Return true if you wish to prevent a user from picking up an item
     */
    public boolean blockPickup(Player p, GroundItem i);
}
