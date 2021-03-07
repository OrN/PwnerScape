package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.player.Player;

public interface InvUseOnPlayerListener {
	
	public void onInvUseOnPlayer(Player player, Player otherPlayer, Item item);

}
