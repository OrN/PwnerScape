package com.pwns.server.plugins.skills;

import com.pwns.server.Server;
import com.pwns.server.event.SingleEvent;
import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.ObjectActionListener;
import com.pwns.server.plugins.listeners.executive.ObjectActionExecutiveListener;
import com.pwns.server.plugins.Functions;

public final class Pick implements ObjectActionExecutiveListener,
		ObjectActionListener {

	@Override
	public boolean blockObjectAction(final GameObject obj,
			final String command, final Player player) {
		return command.equals("pick")
				|| /* Flax */obj.getID() == 313;
	}

	private void handleFlaxPickup(final Player owner, GameObject obj) {
		owner.setBusyTimer(250);
		if (!owner.getInventory().full()) {
			owner.message("You uproot a flax plant");
			Functions.addItem(owner, 675, 1);
		} else {
			owner.message("Your inventory is full!");
		}
	}

	@Override
	public void onObjectAction(final GameObject object, final String command,
			final Player owner) {
		switch (object.getID()) {
		case 72: // Wheat
			owner.message("You get some grain");
			owner.getInventory().add(new Item(29, 1));
			break;
		case 191: // Potatos
			owner.message("You pick a potato");
			owner.getInventory().add(new Item(348, 1));
			break;
		case 313: // Flax
			handleFlaxPickup(owner, object);
			return;
		default:
			owner.message("Nothing interesting happens");
			return;
		}
		owner.playSound("potato");
		owner.setBusy(true);
		Server.getServer().getEventHandler().add(
				new SingleEvent(owner, 200) {
					@Override
					public void action() {
						owner.setBusy(false);
					}
				});
		return;
	}
}
