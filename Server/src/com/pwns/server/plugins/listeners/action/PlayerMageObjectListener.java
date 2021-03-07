package com.pwns.server.plugins.listeners.action;

import com.pwns.server.external.SpellDef;
import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;

public interface PlayerMageObjectListener {
    public void onPlayerMageObject(Player player, GameObject obj, SpellDef spell);
}
