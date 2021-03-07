package com.pwns.server.plugins.listeners.executive;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;

public interface InvUseOnNpcExecutiveListener {
	
	public boolean blockInvUseOnNpc(Player player, Npc npc, Item item);

}
