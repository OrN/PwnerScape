package com.pwns.server.plugins.listeners.executive;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.player.Player;

public interface InvUseOnItemExecutiveListener {

    public boolean blockInvUseOnItem(Player player, Item item1, Item item2);

}
