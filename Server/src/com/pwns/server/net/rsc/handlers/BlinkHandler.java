package com.pwns.server.net.rsc.handlers;

import com.pwns.server.model.entity.player.Player;
import com.pwns.server.net.Packet;
import com.pwns.server.net.rsc.PacketHandler;

public class BlinkHandler implements PacketHandler {

	@Override
	public void handlePacket(Packet p, Player player) throws Exception {
		int coordX = p.readShort();
		int coordY = p.readShort();
		if(player.isMod()) 
			player.teleport(coordX, coordY);
	}

}
