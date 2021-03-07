package com.pwns.server.plugins.listeners.executive;

import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;

public interface WallObjectActionExecutiveListener {

    public boolean blockWallObjectAction(GameObject obj, Integer click, Player player);

}
