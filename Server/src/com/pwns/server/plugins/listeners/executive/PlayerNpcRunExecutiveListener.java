package com.pwns.server.plugins.listeners.executive;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;

public interface PlayerNpcRunExecutiveListener {
	  /**
     * Return true if you wish to prevent a user from running from npc
     */
	public boolean blockPlayerNpcRun(Player p, Npc n);
}
