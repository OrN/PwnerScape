package com.pwns.server.content.market.task;

import java.util.ArrayList;

import com.pwns.server.content.market.CollectableItem;
import com.pwns.server.content.market.MarketDatabase;
import com.pwns.server.external.EntityHandler;
import com.pwns.server.external.ItemDefinition;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.net.rsc.ActionSender;

public class CollectableItemsNotificationTask extends MarketTask {
	
	private Player player;

	public CollectableItemsNotificationTask(Player player) {
		this.player = player;
	}

	@Override
	public void doTask() throws Exception {
		ArrayList<CollectableItem> list = MarketDatabase.getCollectableItemsFor(player.getDatabaseID());
		String items = "Following items have been removed from market: % ";
		for (CollectableItem item : list) {
			ItemDefinition def = EntityHandler.getItemDef(item.item_id);
			items += " @lre@" + def.getName() + " @whi@x @cya@" + item.item_amount + " " + item.explanation + "@whi@ %";
		}
		items += "@gre@You can claim them back from Auctioneer";

		if (list.size() == 0) {
			return;
		}
		ActionSender.sendBox(player, items, true);
	}
	

}
