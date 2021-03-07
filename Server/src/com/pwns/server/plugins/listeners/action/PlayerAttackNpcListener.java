package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;

public interface PlayerAttackNpcListener {
	
	 public void onPlayerAttackNpc(Player p, Npc affectedmob);

}
