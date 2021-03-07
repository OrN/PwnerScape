package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.GroundItem;
import com.pwns.server.model.entity.player.Player;

public interface InvUseOnGroundItemListener {

	public void onInvUseOnGroundItem(Item myItem, GroundItem item, Player player);

}
