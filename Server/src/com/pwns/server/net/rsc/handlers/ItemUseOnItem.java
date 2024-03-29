package com.pwns.server.net.rsc.handlers;

import com.pwns.server.Constants;
import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.model.world.World;
import com.pwns.server.net.Packet;
import com.pwns.server.net.rsc.PacketHandler;
import com.pwns.server.plugins.PluginHandler;

public final class ItemUseOnItem implements PacketHandler {
	/**
	 * World instance
	 */
	public static final World world = World.getWorld();

	public void handlePacket(Packet p, Player player) throws Exception {

		if (player.isBusy()) {
			player.resetPath();
			return;
		}
		player.resetAll();
		int itemIndex1 = p.readShort();
		int itemIndex2 = p.readShort();
		Item item1 = player.getInventory().get(itemIndex1);
		Item item2 = player.getInventory().get(itemIndex2);
		
		if (item1 == null || item2 == null) {
			player.setSuspiciousPlayer(true);
			return;
		}
		if(itemIndex1 == itemIndex2) {
			player.message("Nothing interesting happens");
			return;
		}
		
		if (item1.getDef().isMembersOnly() || item2.getDef().isMembersOnly()) {
			if (!Constants.GameServer.MEMBER_WORLD) {
				player.sendMemberErrorMessage();
				return;
			}
		}

		// Services.lookup(DatabaseManager.class).addQuery(new
		// GenericLog(player.getUsername() + " used item " + item1 + " on item "
		// + item2 + " at " + player.getLocation()));

		if (PluginHandler.getPluginHandler().blockDefaultAction("InvUseOnItem",
				new Object[] { player, item1, item2 })) {
			return;
		}

		 player.message("Nothing interesting happens");
	}
}
