package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.player.Player;

public interface DropListener {
    /**
     * Called when a user drops an item
     */
    public void onDrop(Player p, Item i);
}
