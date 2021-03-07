package com.pwns.server.plugins.misc;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.InvUseOnObjectListener;
import com.pwns.server.plugins.listeners.action.ObjectActionListener;
import com.pwns.server.plugins.listeners.executive.InvUseOnObjectExecutiveListener;
import com.pwns.server.plugins.listeners.executive.ObjectActionExecutiveListener;
import com.pwns.server.plugins.Functions;

public class MuddyChest implements ObjectActionExecutiveListener, ObjectActionListener, InvUseOnObjectListener, InvUseOnObjectExecutiveListener {
	
	public final int MUDDY_CHEST = 222;
	public final int MUDDY_KEY = 414;

	@Override
	public void onObjectAction(GameObject obj, String command, Player p) {
		if(obj.getID() == MUDDY_CHEST) {
			p.message("the chest is locked");
		}
	}
	
	@Override
	public void onInvUseOnObject(GameObject obj, Item item, Player p) {
		if(obj.getID() == MUDDY_CHEST && item.getID() == MUDDY_KEY) {
			Functions.removeItem(p, MUDDY_KEY, 1);
			Functions.message(p, "you unlock the chest with your key");
			p.message("You find some treasure in the chest");
			Functions.openChest(obj, 3000, 221);
			Functions.addItem(p, 158, 1); // uncut ruby (1)
			Functions.addItem(p, 173, 1); // mithril bar (1)
			Functions.addItem(p, 42, 2); // law rune (2)
			Functions.addItem(p, 327, 1); // anchovie pizza (1)
			Functions.addItem(p, 64, 1); // mithril dagger (1)
			Functions.addItem(p, 10, 50); // coins (50)
			Functions.addItem(p, 38, 2); // death-rune (2)
			Functions.addItem(p, 41, 10); // chaos-rune (10)
		}
	}

	@Override
	public boolean blockObjectAction(GameObject obj, String command, Player p) {
		if(obj.getID() == MUDDY_CHEST) {
			return true;
		}
		return false;
	}

	@Override
	public boolean blockInvUseOnObject(GameObject obj, Item item, Player p) {
		if(obj.getID() == MUDDY_CHEST && item.getID() == MUDDY_KEY) {
			return true;
		}
		return false;
	}
}