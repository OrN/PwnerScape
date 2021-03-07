package com.pwns.server.plugins.npcs.alkharid;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class Warrior implements TalkToNpcListener, TalkToNpcExecutiveListener {

	public final int WARRIOR = 86;

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == WARRIOR) {
			return true;
		}
		return false;
	}

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		if(n.getID() == WARRIOR) {
			int chatRandom = p.getRandom().nextInt(17);
			Functions.playerTalk(p, n, "Hello", "How's it going?");
			if(chatRandom == 0) {
				Functions.npcTalk(p, n, "Very well, thank you");
			} else if(chatRandom == 1) {
				Functions.npcTalk(p, n, "How can I help you?");
				int menu = Functions.showMenu(p, n,
						"Do you wish to trade?",
						"I'm in search of a quest",
						"I'm in search of enemies to kill");
				if(menu == 0) {
					Functions.npcTalk(p, n, "No, I have nothing I wish to get rid of",
							"If you want to do some trading,",
							"there are plenty of shops and market stalls around though");
				} else if(menu == 1) {
					Functions.npcTalk(p, n, "I'm sorry I can't help you there");
				} else if(menu == 2) {
					Functions.npcTalk(p, n, "I've heard there are many fearsome creatures under the ground");
				} 
			} else if(chatRandom == 2) {
				Functions.npcTalk(p, n, "None of your business");
			} else if(chatRandom == 3) {
				Functions.npcTalk(p, n, "No, I don't want to buy anything");
			} else if(chatRandom == 4) {
				Functions.npcTalk(p, n, "Get out of my way",
						"I'm in a hurry");
			} else if(chatRandom == 5) {
				Functions.npcTalk(p, n, "Who are you?");
				Functions.playerTalk(p, n, "I am a bold adventurer");
				Functions.npcTalk(p, n, "A very noble profession");
			} else if(chatRandom == 6) {
				Functions.npcTalk(p, n, "I'm fine",
						"How are you?");
				Functions.playerTalk(p, n, "Very well, thank you");
			} else if(chatRandom == 7) {
				Functions.npcTalk(p, n, "Hello",
						"Nice weather we've been having");
			} else if(chatRandom == 8) {
				Functions.npcTalk(p, n, "Do I know you?");
				Functions.playerTalk(p, n, "No, I was just wondering if you had anything interesting to say");
			} else if(chatRandom == 9) {
				Functions.npcTalk(p, n, "Not too bad",
						"I'm a little worried about the increase in Goblins these days");
				Functions.playerTalk(p, n, "Don't worry. I'll kill them");

			} else if(chatRandom == 10) {
				Functions.npcTalk(p, n, "No, I don't have any spare change");
			} else if(chatRandom == 11) {
				Functions.playerTalk(p, n, "I'm in search of enemies to kill");
				Functions.npcTalk(p, n, "I've heard there are many fearsome creatures under the ground");
			} else if(chatRandom == 12) {
				Functions.playerTalk(p, n, "Do you wish to trade?");
				Functions.npcTalk(p, n, "No, I have nothing I wish to get rid of",
						"If you want to do some trading,",
						"there are plenty of shops and market stalls around though");
			} else if(chatRandom == 13) {
				Functions.npcTalk(p, n, "Not too bad");
			} else if(chatRandom == 14) {
				p.message("The man ignores you");
			} else if(chatRandom == 15) {
				Functions.npcTalk(p, n, "Have this flier");
				Functions.addItem(p, 201, 1);
			} else if(chatRandom == 16) {
				Functions.npcTalk(p, n, "Hello");
			}
		}
	}
}
