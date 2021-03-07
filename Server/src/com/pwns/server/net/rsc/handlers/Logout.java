package com.pwns.server.net.rsc.handlers;

import com.pwns.server.model.entity.player.Player;
import com.pwns.server.net.Packet;
import com.pwns.server.net.rsc.ActionSender;
import com.pwns.server.net.rsc.PacketHandler;
import com.pwns.server.plugins.PluginHandler;

public final class Logout implements PacketHandler {

	public void handlePacket(Packet p, Player player) throws Exception {

		if (PluginHandler.getPluginHandler().blockDefaultAction("PlayerLogout",
				new Object[] { player }, false)) {
			ActionSender.sendCantLogout(player);
			return;
		}
		player.unregister(false, "Player logged out");
	}
}
