package com.pwns.server.plugins.itemactions;

import static com.pwns.server.plugins.Functions.inArray;
import static com.pwns.server.plugins.Functions.removeItem;
import static com.pwns.server.plugins.Functions.showBubble;
import static com.pwns.server.plugins.Functions.sleep;

import com.pwns.server.event.custom.BatchEvent;
import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.InvUseOnObjectListener;
import com.pwns.server.plugins.listeners.executive.InvUseOnObjectExecutiveListener;
import com.pwns.server.plugins.Functions;

public class Refill implements InvUseOnObjectListener,
        InvUseOnObjectExecutiveListener {

	final int[] VALID_OBJECTS = { 2, 466, 814, 48, 26, 86, 1130 };
	final int[] REFILLABLE = { 21, 140, 341, 465 };
	final int[] REFILLED = { 50, 141, 342, 464 };

	@Override
	public boolean blockInvUseOnObject(GameObject obj, Item item, Player player) {
		return Functions.inArray(obj.getID(), 2, 466, 814, 48, 26, 86, 1130) && Functions.inArray(item.getID(), 21, 140, 341, 465);
	}

	@Override
	public void onInvUseOnObject(GameObject obj, final Item item, Player player) {
		for (int i = 0; i < REFILLABLE.length; i++) {
			if (REFILLABLE[i] == item.getID()) {
				final int itemID = item.getID();
				final int refilledID = REFILLED[i];
				player.setBatchEvent(new BatchEvent(player, 300, player.getInventory().countId(itemID)) {
					@Override
					public void action() {
						if (Functions.removeItem(owner, itemID, 1)) {
							Functions.showBubble(owner, item);
							owner.playSound("filljug");
							Functions.sleep(300);
							owner.message("You fill the " + item.getDef().getName().toLowerCase() + " from the " + obj.getGameObjectDef().getName().toLowerCase());
							Functions.addItem(owner, refilledID, 1);
						} else {
							interrupt();
						}
					}
				});
				break;
			}
		}
	}

}
