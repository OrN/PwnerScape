package com.pwns.server.plugins.listeners.executive;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;

public interface PlayerAttackNpcExecutiveListener {
	
	public boolean blockPlayerAttackNpc(Player p, Npc n);

}
