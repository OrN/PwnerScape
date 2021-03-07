package com.pwns.server.plugins.listeners.executive;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;

public interface PlayerRangeNpcExecutiveListener {

    /**
     * Return true if you wish to prevent a user from ranging a player
     */
    public boolean blockPlayerRangeNpc(Player p, Npc n);
}
