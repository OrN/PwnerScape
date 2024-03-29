package com.pwns.server.plugins.npcs.seers;

import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class Stankers implements TalkToNpcExecutiveListener, TalkToNpcListener {
	
	public static int STANKERS = 389; 

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		if(n.getID() == STANKERS) {
			Functions.npcTalk(p,n, "Hello bold adventurer");
			int menu = Functions.showMenu(p,n,
					"Are these your trucks?",
					"Hello Mr Stankers");
			if(menu == 0) {
				Functions.npcTalk(p,n, "Yes, I use them to transport coal over the river",
						"I will let other people use them too",
						"I'm a nice person like that",
						"Just put coal in a truck and I'll move it down to my depot over the river");
			} else if(menu == 1) {
				Functions.npcTalk(p,n, "Would you like a poison chalice?");
				int subMenu = Functions.showMenu(p,n,
				"Yes please",
				"what's a poison chalice?",
				"no thankyou");
				if(subMenu == 0) {
					p.message("Stankers hands you a glass of strangely coloured liquid");
					Functions.addItem(p, 737, 1);
				} else if(subMenu == 1) {
					Functions.npcTalk(p,n, "It's an exciting drink I've invented",
							"I don't know what it tastes like",
							"I haven't tried it myself");
				}
			}
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == STANKERS;
	}
}