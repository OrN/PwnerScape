package com.pwns.server.plugins.listeners.executive;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;

public interface TalkToNpcExecutiveListener {
    /**
     * Return true to block a player from talking to a npc
     */
    public boolean blockTalkToNpc(Player p, Npc n);
}
