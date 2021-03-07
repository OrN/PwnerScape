package com.pwns.server.plugins.misc;

import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.ObjectActionListener;
import com.pwns.server.plugins.listeners.executive.ObjectActionExecutiveListener;
import com.pwns.server.plugins.Functions;

public class LeafyPalmTree implements ObjectActionListener, ObjectActionExecutiveListener {

	@Override
	public boolean blockObjectAction(GameObject obj, String command, Player p) {
		if(obj.getID() == 1176) {
			return true;
		}
		return false;
	}

	@Override
	public void onObjectAction(GameObject obj, String command, Player p) {
		if(obj.getID() == 1176) {
			Functions.message(p, 1300, "You give the palm tree a good shake.");
			Functions.message(p, 0, "A palm leaf falls down.");
			Functions.createGroundItem(1279, 1, obj.getX(), obj.getY(), p);
			Functions.replaceObjectDelayed(obj, 15000, 33);
		}
	}
}
