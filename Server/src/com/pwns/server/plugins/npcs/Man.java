package com.pwns.server.plugins.npcs;

import static com.pwns.server.plugins.Functions.inArray;
import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class Man implements TalkToNpcListener, TalkToNpcExecutiveListener {

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return Functions.inArray(n.getID(), 11, 63, 72);
	}

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		int selected = p.getRandom().nextInt(13);

		Functions.playerTalk(p, n, "Hello", "How's it going?");

		if (selected == 0)
			Functions.npcTalk(p, n, "Get out of my way", "I'm in a hurry");
		else if (selected == 1)
			p.message("The man ignores you");
		else if (selected == 2)
			Functions.npcTalk(p, n, "Not too bad");
		else if (selected == 3)
			Functions.npcTalk(p, n, "Very well, thank you");
		else if (selected == 4) {
			Functions.npcTalk(p, n, "Have this flier");
			Functions.addItem(p, 201, 1);
		} else if (selected == 5)
			Functions.npcTalk(p, n, "I'm a little worried",
					"I've heard there's lots of people going about,",
					"killing citizens at random");
		else if (selected == 6) {
			Functions.npcTalk(p, n, "I'm fine", "How are you?");
			Functions.playerTalk(p, n, "Very well, thank you");
		} else if (selected == 7)
			Functions.npcTalk(p, n, "Hello");
		else if (selected == 8) {
			Functions.npcTalk(p, n, "Who are you?");
			Functions.playerTalk(p, n, "I am a bold adventurer");
			Functions.npcTalk(p, n, "A very noble profession");
		} else if (selected == 9) {
			Functions.npcTalk(p, n, "Not too bad",
					"I'm a little worried about the increase in Goblins these days");
			Functions.playerTalk(p, n, "Don't worry. I'll kill them");
		} else if (selected == 10)
			Functions.npcTalk(p, n, "Hello", "Nice weather we've been having");
		else if (selected == 11)
			Functions.npcTalk(p, n, "No, I don't want to buy anything");
		else if (selected == 12) {
			Functions.npcTalk(p, n, "Do I know you?");
			Functions.playerTalk(p, n,
					"No, I was just wondering if you had anything interesting to say");
		} else if (selected == 13) {
			Functions.npcTalk(p, n, "How can I help you?");
			int option = Functions.showMenu(p, n, "Do you wish to trade?",
					"I'm in search of a quest",
					"I'm in search of enemies to kill");
			if (option == 0)
				Functions.npcTalk(p, n, "No, I have nothing I wish to get rid of",
						"If you want some trading,",
						"there are plenty of shops and market stalls around though");
			else if (option == 1)
				Functions.npcTalk(p, n, "I'm sorry I can't help you there");
			else if (option == 2)
				Functions.npcTalk(p, n,
						"I've heard there are many fearsome creatures under the ground");
		}
	}
}
