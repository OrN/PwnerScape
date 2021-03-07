package com.pwns.server.plugins.npcs.shilo;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class Serevel implements TalkToNpcExecutiveListener, TalkToNpcListener {

	public static final int SEREVEL = 623;

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		if(n.getID() == SEREVEL) {
			Functions.playerTalk(p, n, "Hello");
			Functions.npcTalk(p, n, "Hello Bwana.",
					"Are you interested in buying a ticket for the 'Lady of the Waves'?",
					"It's a ship that can take you to either Port Sarim or Khazard Port",
					"The ship lies west of Shilo Village and south of Cairn Island.",
					"The tickets cost 100 Gold Pieces.",
					"Would you like to purchase a ticket Bwana?");
			int menu = Functions.showMenu(p, n,
					"Yes, that sounds great!",
					"No thanks.");
			if(menu == 0) {
				if(Functions.hasItem(p, 10, 100)) {
					Functions.removeItem(p, 10, 100);
					Functions.npcTalk(p, n, "Great, nice doing business with you.");
					Functions.addItem(p, 988, 1);
				} else {
					Functions.npcTalk(p, n, "Sorry Bwana, you don't have enough money.",
							"Come back when you have 100 Gold Pieces.");
				}
			} else if(menu == 1) {
				Functions.npcTalk(p, n, "Fair enough Bwana, let me know if you change your mind.");
			}
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == SEREVEL) {
			return true;
		}
		return false;
	}

}
