package com.pwns.server.content.market.task;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pwns.server.content.market.CollectableItem;
import com.pwns.server.content.market.MarketDatabase;
import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pwns.server.Constants;
import com.pwns.server.net.rsc.ActionSender;

public class PlayerCollectItemsTask extends MarketTask {
	
	/**
     * The asynchronous logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();
	
	private Player player;

	public PlayerCollectItemsTask(Player p) {
		this.player = p;
	}
	
	@Override
	public void doTask() throws Exception {
		if (player.isPVP()) {
			player.message("You cannot collect your items while flagged for @red@PVP@whi@!");
			return;
		}

		ArrayList<CollectableItem> list = MarketDatabase.getCollectableItemsFor(player.getDatabaseID());

		if (list.size() == 0) {
			player.message("You have no items to collect.");
			return;
		}

		String items = "Following items have been inserted to your bank: % ";
		try {
			PreparedStatement setCollected = MarketDatabase.databaseInstance
					.prepareStatement("UPDATE `" + Constants.GameServer.MYSQL_TABLE_PREFIX
						+ "expired_auctions` SET `claim_time`= '" + System.currentTimeMillis()
							+ "',`claimed`='1' WHERE `claim_id`=?");
			for (CollectableItem i : list) {
				Item item = new Item(i.item_id, i.item_amount);
				if (!player.getBank().canHold(item)) {
					items += "@gre@Rest of the items are still held by auctioneer% make more space in bank and claim.";
					break;
				}
				player.getBank().add(item);
				items += " @lre@" + item.getDef().getName() + " @whi@x @cya@" + item.getAmount() + "@whi@ "
						+ i.explanation + " %";
				setCollected.setInt(1, i.claim_id);
				setCollected.addBatch();
			}
			setCollected.executeBatch();
		} catch (SQLException e) {
			LOGGER.catching(e);
		}
		ActionSender.sendBox(player, items, true);
	}

}
