package com.pwns.server.plugins.minigames.fishingtrawler;

import static com.pwns.server.plugins.Functions.message;
import static com.pwns.server.plugins.Functions.removeItem;

import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.ObjectActionListener;
import com.pwns.server.plugins.listeners.executive.ObjectActionExecutiveListener;
import com.pwns.server.plugins.Functions;

public class FillHole implements ObjectActionExecutiveListener, ObjectActionListener {

	@Override
	public void onObjectAction(GameObject obj, String command, Player player) {
		player.setBusyTimer(650);
		if(Functions.removeItem(player, 785, 1)) {
			Functions.removeObject(obj);
			Functions.message(player, 0, "you fill the hole with swamp paste");
		} else {
			Functions.message(player, 0, "you'll need some swamp paste to fill that");
		}
	}

	@Override
	public boolean blockObjectAction(GameObject obj, String command, Player player) {
		return obj.getID() == 1077 || obj.getID() == 1071;
	}
}
