package com.pwns.server.plugins.minigames.gnomerestaurant;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.GroundItem;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.InvActionListener;
import com.pwns.server.plugins.listeners.action.PickupListener;
import com.pwns.server.plugins.listeners.executive.InvActionExecutiveListener;
import com.pwns.server.plugins.listeners.executive.PickupExecutiveListener;
import com.pwns.server.util.rsc.DataConversions;
import com.pwns.server.plugins.Functions;

public class SwampToads implements PickupListener, PickupExecutiveListener, InvActionListener, InvActionExecutiveListener {

	public static final int SWAMP_TOAD = 895;
	public static final int TOAD_LEGS = 896;
	
	@Override
	public boolean blockInvAction(Item item, Player p) {
		if(item.getID() == SWAMP_TOAD) {
			return true;
		}
		return false;
	}

	@Override
	public void onInvAction(Item item, Player p) {
		if(item.getID() == SWAMP_TOAD) {
			Functions.message(p, 1900, "you pull the legs off the toad");
			p.message("poor toad..at least they'll grow back");
			p.getInventory().replace(item.getID(), TOAD_LEGS);
		}
	}

	@Override
	public boolean blockPickup(Player p, GroundItem i) {
		if(i.getID() == SWAMP_TOAD) {
			return true;
		}
		return false;
	}

	@Override
	public void onPickup(Player p, GroundItem i) {
		if(i.getID() == SWAMP_TOAD) {
			if(DataConversions.random(0, 10) >= 3) {
				p.message("you pick up the swamp toad");
				Functions.message(p, 1900, "but it jumps out of your hands..");
				p.message("..slippery little blighters");
			} else {
				Functions.message(p, 1900, "you pick up the swamp toad");
				i.remove();
				Functions.addItem(p, 895, 1);
				p.message("you just manage to hold onto it");
			}
		}
	}
}
