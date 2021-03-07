package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;

public interface NpcCommandListener {

	public void onNpcCommand(Npc n, String command, Player p);
	
}
