package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;

public interface InvUseOnObjectListener {

    /**
     * Called when a user uses an inventory item on an game object
     */
    public void onInvUseOnObject(GameObject obj, Item item, Player player);
}
