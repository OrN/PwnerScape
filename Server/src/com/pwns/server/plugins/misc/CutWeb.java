package com.pwns.server.plugins.misc;

import static com.pwns.server.plugins.Functions.message;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.InvUseOnWallObjectListener;
import com.pwns.server.plugins.listeners.executive.InvUseOnWallObjectExecutiveListener;
import com.pwns.server.util.rsc.Formulae;
import com.pwns.server.plugins.Functions;

public class CutWeb implements InvUseOnWallObjectListener, InvUseOnWallObjectExecutiveListener {

	public static int WEB = 24;

	@Override
	public boolean blockInvUseOnWallObject(GameObject obj, Item item, Player p) {
		if(obj.getID() == WEB) {
			return true;
		}
		return false;
	}

	@Override
	public void onInvUseOnWallObject(GameObject obj, Item item, Player p) {
		if(obj.getID() == WEB) {
			if(item.getDef().getWieldPosition() != 4 && item.getID() != 13) {
				p.message("Nothing interesting happens");
				return;
			}	
			Functions.message(p, "You try to destroy the web...");
			if (Formulae.cutWeb()) {
				p.message("You slice through the web");
				Functions.removeObject(obj);
				Functions.delayedSpawnObject(obj.getLoc(), 30000);
			} else {
				p.message("You fail to cut through it");
			}
		}
	}
}
