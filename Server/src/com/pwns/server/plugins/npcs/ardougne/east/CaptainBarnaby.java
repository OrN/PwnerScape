package com.pwns.server.plugins.npcs.ardougne.east;

import static com.pwns.server.plugins.Functions.message;
import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.showMenu;
import static com.pwns.server.plugins.Functions.sleep;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class CaptainBarnaby implements TalkToNpcExecutiveListener,
        TalkToNpcListener {

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		Functions.npcTalk(p, n, "Do you want to go on a trip to Karamja?",
				"The trip will cost you 30 gold");
		int karamja = Functions.showMenu(p, n, "Yes please", "No thankyou");
		if (karamja == 0) {
			if (p.getInventory().remove(10, 30) > -1) { // enough money
				Functions.message(p, "You pay 30 gold", "You board the ship");
				p.teleport(467, 651, false);
				Functions.sleep(1000);
				p.message("The ship arrives at Karamja");
			} else {
				Functions.playerTalk(p, n, "Oh dear I don't seem to have enough money");
			}
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 316;
	}

}
