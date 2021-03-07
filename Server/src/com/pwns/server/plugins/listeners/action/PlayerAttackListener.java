package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.entity.player.Player;

public interface PlayerAttackListener {

    public void onPlayerAttack(Player p, Player affectedmob);
}
