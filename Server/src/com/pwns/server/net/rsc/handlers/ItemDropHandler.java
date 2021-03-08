package com.pwns.server.net.rsc.handlers;

import com.pwns.server.Server;
import com.pwns.server.event.DelayedEvent;
import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.GroundItem;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.model.states.Action;
import com.pwns.server.model.world.World;
import com.pwns.server.net.Packet;
import com.pwns.server.net.rsc.ActionSender;
import com.pwns.server.net.rsc.PacketHandler;
import com.pwns.server.plugins.PluginHandler;
import com.pwns.server.sql.GameLogging;
import com.pwns.server.sql.query.logs.GenericLog;
import com.pwns.server.util.rsc.DataConversions;

public final class ItemDropHandler implements PacketHandler {

	public void handlePacket(Packet p, Player player) throws Exception {
		if (player.isBusy()) {
			player.resetPath();
			return;
		}
		if (player.getStatus() == Action.DROPPING_GITEM) {
			return;
		}
		player.resetAll();
		final int idx = (int) p.readShort();
		int amount = p.readInt();

		if (idx < 0 || idx >= player.getInventory().size()) {
			player.setSuspiciousPlayer(true);
			return;
		}
		final Item item = player.getInventory().get(idx);

		if (item == null) {
			player.setSuspiciousPlayer(true);
			return;
		}
		if (amount <= 0) {
			return;
		}

		if (item.getDef().isStackable()) {
			if (amount > item.getAmount()) {
				amount = item.getAmount();
			}
		} else {
			if (amount > player.getInventory().countId(item.getID())) {
				amount = player.getInventory().countId(item.getID());
			}
		}

		final int finalAmount = amount;

		Server.getServer().getEventHandler().add(new DelayedEvent(player, 0) {
			@Override
			public void run() {
				if (!owner.hasMoved()) {
					if (item.getDef().isStackable()) {
						dropStackable(owner, item, finalAmount);
					} else {
						dropUnstackable(owner, item, finalAmount);
					}
					stop();
				}
			}
		});

	}

	public void dropStackable(final Player player, final Item item, final int amount) {
		if (!item.getDef().isStackable()) {
			throw new IllegalArgumentException("Item must be stackable when passed on to dropStackable()");
		}

		player.setStatus(Action.DROPPING_GITEM);

		if (!player.getInventory().contains(item) || player.getStatus() != Action.DROPPING_GITEM) {
			player.setStatus(Action.IDLE);
			return;
		}
		if (PluginHandler.getPluginHandler().blockDefaultAction("Drop", new Object[] { player, item })) {
			return;
		}
		if (player.getInventory().remove(item.getID(), amount) > -1) {
			GroundItem groundItem = new GroundItem(item.getID(), player.getX(), player.getY(), amount,
					player);
			ActionSender.sendSound(player, "dropobject");
			World.getWorld().registerItem(groundItem);
			GameLogging.addQuery(new GenericLog(player.getUsername() + " dropped " + item.getDef().getName() + " x"
					+ DataConversions.numberFormat(groundItem.getAmount()) + " at " + player.getLocation().toString()));
			player.setStatus(Action.IDLE);
		}
	}

	public void dropUnstackable(final Player player, final Item item, final int amount) {
		if (item.getDef().isStackable()) {
			throw new IllegalArgumentException("Item must be unstackable when passed on to dropUnstackable()");
		}

		player.setStatus(Action.DROPPING_GITEM);
		if (!player.getInventory().contains(item) || player.getStatus() != Action.DROPPING_GITEM) {
			player.setStatus(Action.IDLE);
			return;
		}
		if (!player.getInventory().hasItemId(item.getID())) {
			player.message("You don't have the entered amount to drop");
			player.setStatus(Action.IDLE);
			return;
		}
		ActionSender.sendSound(player, "dropobject");
		if (PluginHandler.getPluginHandler().blockDefaultAction("Drop", new Object[] { player, item })) {
			//TODO I GUESS???? if plugins allow to drop aswell and not completely stop?
			//Not sure though - handle it all via plugins for specific blockAction.
			player.setStatus(Action.IDLE);
			return;
		}
		for (int i = 0; i < amount; i++) {
			if (player.getInventory().remove(item) <= 0)
				break;
			GroundItem groundItem = new GroundItem(item.getID(), player.getX(), player.getY(), amount,
					player);
			World.getWorld().registerItem(groundItem);
			GameLogging.addQuery(new GenericLog(player.getUsername() + " dropped " + item.getDef().getName()
					+ " at " + player.getLocation().toString()));
		}

		if (amount > 1)
			player.message("Dropped " + amount);

		player.setStatus(Action.IDLE);
	}
}
