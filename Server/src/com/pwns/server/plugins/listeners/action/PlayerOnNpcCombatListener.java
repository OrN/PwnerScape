package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;

public interface PlayerOnNpcCombatListener {
	public void onPlayerOnNpcCombat(Player p, Npc n);
}
