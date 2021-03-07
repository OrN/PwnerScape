package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.player.Player;

public interface WieldListener {

    public void onWield(Player player, Item item);
}
