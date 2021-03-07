package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.player.Player;

public interface UnWieldListener {

    public void onUnWield(Player player, Item item);

}
