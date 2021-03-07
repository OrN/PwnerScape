package com.pwns.server.plugins.npcs.shilo;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class Kaleb implements TalkToNpcExecutiveListener, TalkToNpcListener {

	public static final int KALEB = 621;

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		if(n.getID() == KALEB) {
			Functions.playerTalk(p, n, "Hello.");
			Functions.npcTalk(p, n, "Hello Bwana,",
					"What can I do for you today?");
			int menu = Functions.showMenu(p, n,
					"Can you tell me a bit about this place?",
					"Buy some wine : 1 Gold.",
					"Buy some Beer: 2 Gold.",
					"Buy a nights rest: 35 Gold",
					"Buy a pack of 5 Dorm tickets: 175 Gold");
			if(menu == 0) {
				Functions.npcTalk(p, n, "Of course Bwana, you look like a traveler!");
				Functions.playerTalk(p, n, "Yes I am actually!");
				Functions.npcTalk(p, n, "Well, I am a traveller myself, and I have set up this hostel",
						"for adventurers and travellers who are weary from their journey",
						"There is a dormitory upstairs if you are tired, it costs 35 gold",
						"pieces which covers the costs of laundry and cleaning.");
			} else if(menu == 1) {
				Functions.npcTalk(p, n, "Very good " +(p.isMale() ? "sir" : "madam!") + "!");
				if(Functions.hasItem(p, 10, 1)) {
					Functions.removeItem(p, 10, 1);
					Functions.addItem(p, 142, 1);
					p.message("You purchase a jug of wine.");
				} else {
					Functions.npcTalk(p, n, "Sorry Bwana, you don't have enough money.");
				}
			} else if(menu == 2) {
				Functions.npcTalk(p, n, "Very good " +(p.isMale() ? "sir" : "madam!") + "!");
				if(Functions.hasItem(p, 10, 2)) {
					Functions.removeItem(p, 10, 2);
					Functions.addItem(p, 193, 1);
					p.message("You purchase a frothy glass of beer.");
				} else {
					Functions.npcTalk(p, n, "Sorry Bwana, you don't have enough money.");
				}
			} else if(menu == 3) {
				Functions.npcTalk(p, n, "Very good " +(p.isMale() ? "sir" : "madam!") + "!");
				if(Functions.hasItem(p, 10, 35)) {
					Functions.removeItem(p, 10, 35);
					Functions.addItem(p, 987, 1);
					p.message("You purchase a ticket to access the dormitory.");
				} else {
					Functions.npcTalk(p, n, "Sorry Bwana, you don't have enough money.");
				}
			} else if(menu == 5) {
				Functions.npcTalk(p, n, "Very good " +(p.isMale() ? "sir" : "madam!") + "!");
				if(Functions.hasItem(p, 10, 175)) {
					Functions.removeItem(p, 10, 175);
					Functions.addItem(p, 987, 5);
					p.message("You purchase 5 tickets to access the dormitory.");
				} else {
					Functions.npcTalk(p, n, "Sorry Bwana, you don't have enough money.");
				}
			}
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == KALEB) {
			return true;
		}
		return false;
	}

}
