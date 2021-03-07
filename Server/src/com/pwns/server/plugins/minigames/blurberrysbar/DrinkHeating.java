package com.pwns.server.plugins.minigames.blurberrysbar;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.InvUseOnObjectListener;
import com.pwns.server.plugins.listeners.executive.InvUseOnObjectExecutiveListener;
import com.pwns.server.plugins.Functions;

public class DrinkHeating implements InvUseOnObjectListener, InvUseOnObjectExecutiveListener {

	@Override
	public boolean blockInvUseOnObject(GameObject obj, Item item, Player p) {
		if(item.getID() == 854 && obj.getID() == 119) {
			return true;
		}
		if((item.getID() == 853 || item.getID() == 867) && obj.getID() == 119) {
			return true;
		}
		return false;
	}

	@Override
	public void onInvUseOnObject(GameObject obj, Item item, Player p) {
		if(item.getID() == 854 && obj.getID() == 119) {
			Functions.message(p, "you briefly place the drink in the oven");
			p.message("you remove the warm drink");
			if(p.getCache().hasKey("drunk_dragon_base") && p.getCache().hasKey("diced_pa_to_drink") && p.getCache().hasKey("cream_into_drink")) {
				p.getInventory().replace(854, 872);
				Functions.checkAndRemoveBlurberry(p, true);
			} 
			if(p.getCache().hasKey("chocolate_saturday_base") && p.getCache().hasKey("choco_bar_in_drink")) {
				if(Functions.checkAndRemoveBlurberry(p, true)) {
					p.getCache().store("heated_choco_saturday", true);
				}
			} else {
				p.getInventory().replace(854, 867);
			}
		}
		if((item.getID() == 853 || item.getID() == 867) && obj.getID() == 119) {
			Functions.message(p, "you briefly place the drink in the oven");
			p.message("you remove the warm drink");
		}
	}
}
