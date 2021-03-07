package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.player.Player;

public interface InvUseOnItemListener {

    public void onInvUseOnItem(Player player, Item item1, Item item2);
}
