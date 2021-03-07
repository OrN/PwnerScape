package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;

public interface PlayerKilledNpcListener {
    public void onPlayerKilledNpc(Player p, Npc n);
}
