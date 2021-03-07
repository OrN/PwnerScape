package com.pwns.server.plugins.listeners.executive;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;

public interface NpcCommandExecutiveListener {
	
	public boolean blockNpcCommand(Npc n, String command, Player p);

}
