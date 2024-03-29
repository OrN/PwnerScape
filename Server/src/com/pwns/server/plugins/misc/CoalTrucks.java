package com.pwns.server.plugins.misc;

import static com.pwns.server.plugins.Functions.removeItem;
import static com.pwns.server.plugins.Functions.sleep;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.InvUseOnObjectListener;
import com.pwns.server.plugins.listeners.action.ObjectActionListener;
import com.pwns.server.plugins.listeners.executive.InvUseOnObjectExecutiveListener;
import com.pwns.server.plugins.listeners.executive.ObjectActionExecutiveListener;
import com.pwns.server.plugins.Functions;

public class CoalTrucks implements ObjectActionExecutiveListener, ObjectActionListener, InvUseOnObjectListener, InvUseOnObjectExecutiveListener {

	public static int COAL_TRUCK = 383;
	public static int COAL = 155;


	@Override
	public void onObjectAction(GameObject obj, String command, Player p) {
		if(obj.getID() == COAL_TRUCK) {
			if(p.getCache().hasKey("coal_truck") && p.getCache().getInt("coal_truck") > 0) {
				p.setBusyTimer(500);
				int coalLeft = p.getCache().getInt("coal_truck");
				p.message("You remove a piece of coal from the truck");
				Functions.addItem(p, COAL, 1);
				p.getCache().set("coal_truck", coalLeft - 1);
			} else {
				p.message("there is no coal left in the truck");
			}
		}
	}

	@Override
	public boolean blockObjectAction(GameObject obj, String command, Player p) {
		if(obj.getID() == COAL_TRUCK) {
			return true;
		}
		return false;
	}

	@Override
	public boolean blockInvUseOnObject(GameObject obj, Item item, Player p) {
		if(obj.getID() == COAL_TRUCK && item.getID() == COAL) {
			return true;
		}
		return false;
	}

	@Override
	public void onInvUseOnObject(GameObject obj, Item item, Player p) {
		if(obj.getID() == COAL_TRUCK && item.getID() == COAL) {
			p.setBusy(true);
			int coalAmount = p.getInventory().countId(COAL);
			for(int i = 0; i < coalAmount; i++) {
				if(p.getCache().hasKey("coal_truck")) {
					if(p.getCache().getInt("coal_truck") >= 120) {
						p.message("The coal truck is full");
						break;
					}
					int coalDeposited = p.getCache().getInt("coal_truck");
					p.getCache().set("coal_truck", coalDeposited + 1);
				} else {
					p.getCache().set("coal_truck", coalAmount);
				}
				p.message("You put a piece of coal in the truck");
				Functions.removeItem(p, COAL, 1);
				Functions.sleep(50);
			}
			p.setBusy(false);
		}	
	}
}
