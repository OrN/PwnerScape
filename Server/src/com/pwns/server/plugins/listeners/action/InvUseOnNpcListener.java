package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;

public interface InvUseOnNpcListener {
	
	public void onInvUseOnNpc(Player player, Npc npc, Item item);

}
