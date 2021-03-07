package com.pwns.server.plugins.npcs.varrock;

import static com.pwns.server.plugins.Functions.hasItem;
import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.removeItem;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class DancingDonkeyInnBartender implements TalkToNpcListener, TalkToNpcExecutiveListener {

	public static int BARTENDER = 520;

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == BARTENDER;
	}

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		if(n.getID() == BARTENDER) {
			Functions.playerTalk(p,n, "hello");
			Functions.npcTalk(p,n, "good day to you, brave adventurer",
					"can i get you a refreshing beer");
			int menu = Functions.showMenu(p,n,
					"yes please",
					"no thanks",
					"how much?");
			if(menu == 0) {
				buyBeer(p, n);
			} else if(menu == 1) {
				Functions.npcTalk(p,n, "let me know if you change your mind");
			} else if(menu == 2) {
				Functions.npcTalk(p,n, "two gold pieces a pint",
						"so, what do you say?");
				int subMenu = Functions.showMenu(p,n,
						"yes please",
						"no thanks");
				if(subMenu == 0) {
					buyBeer(p, n);
				} else if(subMenu == 1) {
					Functions.npcTalk(p,n, "let me know if you change your mind");
				}
			}
		}
	}
	private void buyBeer(Player p, Npc n) {
		Functions.npcTalk(p,n, "ok then, that's two gold coins please");
		if(Functions.hasItem(p, 10, 2)) {
			p.message("you give two coins to the barman");
			Functions.removeItem(p, 10, 2);
			p.message("he gives you a cold beer");
			Functions.addItem(p, 193, 1);
			Functions.npcTalk(p,n, "cheers");
			Functions.playerTalk(p,n, "cheers");
		} else {
			p.message("you don't have enough gold");
		}
	}
}
