package com.pwns.server.plugins.misc;

import static com.pwns.server.plugins.Functions.showBubble;

import com.pwns.server.event.custom.BatchEvent;
import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.InvUseOnNpcListener;
import com.pwns.server.plugins.listeners.executive.InvUseOnNpcExecutiveListener;
import com.pwns.server.util.rsc.Formulae;
import com.pwns.server.plugins.Functions;

public class Sheep implements InvUseOnNpcListener, InvUseOnNpcExecutiveListener {

	@Override
	public boolean blockInvUseOnNpc(Player player, Npc npc, Item item) {
		return npc.getID() == 2 && item.getID() == 144;
	}

	@Override
	public void onInvUseOnNpc(Player player, Npc npc, Item item) {
		npc.resetPath();
		
		npc.face(player);
		player.face(npc);
		Functions.showBubble(player, item);
		player.message("You attempt to shear the sheep");
		npc.setBusyTimer(1600);
		player.setBatchEvent(new BatchEvent(player, 1500, Formulae.getRepeatTimes(player, Functions.CRAFTING)) {
			@Override
			public void action() {
				npc.setBusyTimer(1600);
				if(Functions.random(0, 4) != 0) {
					player.message("You get some wool");
					Functions.addItem(player, 145, 1);
				} else {
					player.message("The sheep manages to get away from you!");
					npc.setBusyTimer(0);
					interrupt();
					return;
				}
			}
		});
	}
}