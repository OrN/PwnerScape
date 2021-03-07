package com.pwns.server.plugins.listeners.action;

import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;

public interface WallObjectActionListener {
	
	public void onWallObjectAction(GameObject obj, Integer click, Player p);
	
}
