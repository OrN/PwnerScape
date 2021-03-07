package com.pwns.server.net;

import java.util.concurrent.atomic.AtomicReference;

import com.pwns.server.model.entity.player.Player;
import com.pwns.server.net.rsc.ISAACContainer;

public class ConnectionAttachment {
	
	public AtomicReference<Player> player = new AtomicReference<Player>();
	
	public AtomicReference<ISAACContainer> ISAAC = new AtomicReference<ISAACContainer>();
	
}
