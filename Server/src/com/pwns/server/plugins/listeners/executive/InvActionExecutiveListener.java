package com.pwns.server.plugins.listeners.executive;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.player.Player;

public interface InvActionExecutiveListener {
    /**
     * Return true to prevent inventory action
     */
    public boolean blockInvAction(Item item, Player player);
}
