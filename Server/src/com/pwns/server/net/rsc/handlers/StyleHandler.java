package com.pwns.server.net.rsc.handlers;

import com.pwns.server.model.entity.player.Player;
import com.pwns.server.model.world.World;
import com.pwns.server.net.Packet;
import com.pwns.server.net.rsc.PacketHandler;

public class StyleHandler implements PacketHandler {
	/**
	 * World instance
	 */
	public static final World world = World.getWorld();

	public void handlePacket(Packet p, Player player) throws Exception {

		int style = p.readByte();
		if (style < 0 || style > 3) {
			player.setSuspiciousPlayer(true);
			return;
		}
		player.setCombatStyle(style);
	}

}