package com.pwns.server.plugins.listeners.executive;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.player.Player;

public interface InvUseOnPlayerExecutiveListener {
	
	public boolean blockInvUseOnPlayer(Player player, Player  otherPlayer, Item item);

}
