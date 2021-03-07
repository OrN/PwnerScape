package com.pwns.server.plugins.listeners.executive;

import com.pwns.server.external.SpellDef;
import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;

public interface PlayerMageObjectExecutiveListener {
    public boolean blockPlayerMageObject(Player player, GameObject obj, SpellDef spell);
}

