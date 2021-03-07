package com.pwns.server.plugins.misc;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.DropListener;
import com.pwns.server.plugins.listeners.action.InvActionListener;
import com.pwns.server.plugins.listeners.executive.DropExecutiveListener;
import com.pwns.server.plugins.listeners.executive.InvActionExecutiveListener;
import com.pwns.server.plugins.Functions;

public class KittenToCat implements DropListener, DropExecutiveListener, InvActionListener, InvActionExecutiveListener {
	
	private static final int KITTEN = 1096;

	@Override
	public boolean blockDrop(Player p, Item i) {
		if(i.getID() == KITTEN) {
			return true;
		}
		return false;
	}

	@Override
	public void onDrop(Player p, Item i) {
		if(i.getID() == KITTEN) {
			Functions.removeItem(p, KITTEN, 1);
			Functions.message(p, 1200, "you drop the kitten");
			Functions.message(p, 0, "it's upset and runs away");
		}
	}

	@Override
	public boolean blockInvAction(Item item, Player p) {
		if(item.getID() == KITTEN) {
			return true;
		}
		return false;
	}

	@Override
	public void onInvAction(Item item, Player p) {
		if(item.getID() == KITTEN) {
			Functions.message(p, "you softly stroke the kitten",
					"@yel@kitten:..purr..purr..");
			Functions.message(p, 600, "the kitten appreciates the attention");
		}
	}
}
