package com.pwns.server.plugins.listeners.executive;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.GroundItem;
import com.pwns.server.model.entity.player.Player;

public interface InvUseOnGroundItemExecutiveListener {

    public boolean blockInvUseOnGroundItem(Item myItem, GroundItem item, Player player);

}
