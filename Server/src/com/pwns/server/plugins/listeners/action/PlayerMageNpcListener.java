package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;

public interface PlayerMageNpcListener {
    public void onPlayerMageNpc(Player p, Npc n);
}
