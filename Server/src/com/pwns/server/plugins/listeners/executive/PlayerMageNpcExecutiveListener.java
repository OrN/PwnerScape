package com.pwns.server.plugins.listeners.executive;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;

public interface PlayerMageNpcExecutiveListener {

    /**
     * Return true if you wish to prevent a user from ranging a player
     */
    public boolean blockPlayerMageNpc(Player p, Npc n);
}
