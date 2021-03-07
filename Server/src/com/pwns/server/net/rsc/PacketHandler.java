package com.pwns.server.net.rsc;

import com.pwns.server.model.entity.player.Player;
import com.pwns.server.net.Packet;

/**
 * 
 * @author n0m
 *
 */
public interface PacketHandler {
	
    public void handlePacket(Packet p, Player player) throws Exception;
}
