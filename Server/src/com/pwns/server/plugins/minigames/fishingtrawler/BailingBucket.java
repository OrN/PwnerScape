package com.pwns.server.plugins.minigames.fishingtrawler;

import static com.pwns.server.plugins.Functions.sleep;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.model.world.World;
import com.pwns.server.plugins.listeners.action.InvActionListener;
import com.pwns.server.plugins.listeners.executive.InvActionExecutiveListener;
import com.pwns.server.plugins.Functions;

public class BailingBucket implements InvActionExecutiveListener, InvActionListener {

	@Override
	public void onInvAction(Item item, Player player) {
		if (player.isBusy())
			return;
		if (World.getWorld().getFishingTrawler().getShipAreaWater().inBounds(player.getLocation())
				|| World.getWorld().getFishingTrawler().getShipArea().inBounds(player.getLocation())) {
			player.setBusyTimer(650);
			player.message("you bail a little water...");
			Functions.sleep(650);
			World.getWorld().getFishingTrawler().bailWater();
		} else {
			// player.message("");
		}
	}

	@Override
	public boolean blockInvAction(Item item, Player player) {
		return item.getID() == 1282;
	}

}
