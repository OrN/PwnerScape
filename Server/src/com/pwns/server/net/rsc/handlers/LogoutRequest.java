package com.pwns.server.net.rsc.handlers;

import com.pwns.server.model.entity.player.Player;
import com.pwns.server.model.world.World;
import com.pwns.server.net.Packet;
import com.pwns.server.net.rsc.ActionSender;
import com.pwns.server.net.rsc.PacketHandler;

public class LogoutRequest implements PacketHandler {
	/**
	 * World instance
	 */
	public static final World world = World.getWorld();

	public void handlePacket(Packet p, Player player) throws Exception {
		if (player.canLogout()) {
			player.unregister(false, "Player requested log out");
		} else {
			ActionSender.sendCantLogout(player);
		}
	}
}
