package com.pwns.server.plugins.misc;

import static com.pwns.server.plugins.Functions.message;
import static com.pwns.server.plugins.Functions.removeItem;
import static com.pwns.server.plugins.Functions.sleep;

import com.pwns.server.event.rsc.impl.PoisonEvent;
import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.model.world.World;
import com.pwns.server.plugins.listeners.action.InvUseOnObjectListener;
import com.pwns.server.plugins.listeners.action.ObjectActionListener;
import com.pwns.server.plugins.listeners.executive.InvUseOnObjectExecutiveListener;
import com.pwns.server.plugins.listeners.executive.ObjectActionExecutiveListener;
import com.pwns.server.plugins.Functions;

public class SinisterChest implements ObjectActionListener, ObjectActionExecutiveListener, InvUseOnObjectListener, InvUseOnObjectExecutiveListener {

	@Override
	public boolean blockObjectAction(GameObject obj, String command, Player player) {
		if(obj.getID() == 645 && obj.getX() == 617 && obj.getY() == 3567) {
			return true;
		}
		return false;
	}

	@Override
	public void onObjectAction(GameObject obj, String command, Player player) {
		if(obj.getID() == 645 && obj.getX() == 617 && obj.getY() == 3567) {
			player.message("the chest is locked");
		}
	}

	@Override
	public boolean blockInvUseOnObject(GameObject obj, Item item, Player player) {
		if(item.getID() == 932 && obj.getID() == 645 && obj.getX() == 617 && obj.getY() == 3567) {
			return true;
		}
		return false;
	}

	@Override
	public void onInvUseOnObject(GameObject obj, Item item, Player p) {
		if(item.getID() == 932 && obj.getID() == 645 && obj.getX() == 617 && obj.getY() == 3567) {
			Functions.message(p, "you unlock the chest with your key");
			p.message("A foul gas seeps from the chest");
			p.message("You find a lot of herbs in the chest");

			GameObject openedChest = new GameObject(obj.getLocation(), 339, obj.getDirection(), obj.getType());
			GameObject sinisterChest = new GameObject(obj.getLoc());

			World.getWorld().unregisterGameObject(obj);
			World.getWorld().registerGameObject(openedChest);
			Functions.sleep(2000);
			World.getWorld().registerGameObject(sinisterChest);

			Functions.removeItem(p, 932, 1); // remove the sinister key.
			// ADD 9 HERB ITEMS FROM CHEST.
			int[] RandomHerbs = { 435, 436, 437, 438, 439, 440, 441, 442, 443 };
			for (int i = 0; i < 8; i++) {
				int choosenHerbs = (int) (Math.random() * RandomHerbs.length);
				p.getInventory().add(new Item(RandomHerbs[choosenHerbs]));
			}
			// one of the herbs is garanteed torstol.
			Functions.addItem(p, 933, 1);
			// Poison player with damage 6.
			p.startPoisonEvent();
			PoisonEvent poisonEvent = p.getAttribute("poisonEvent", null);
			poisonEvent.setPoisonPower(68);
		}
	}
}
