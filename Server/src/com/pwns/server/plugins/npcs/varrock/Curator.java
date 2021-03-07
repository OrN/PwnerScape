package com.pwns.server.plugins.npcs.varrock;

import com.pwns.server.Constants;
import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.InvUseOnNpcListener;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.InvUseOnNpcExecutiveListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.menu.Menu;
import com.pwns.server.plugins.menu.Option;
import com.pwns.server.plugins.quests.members.digsite.DigsiteExaminer;
import com.pwns.server.plugins.Functions;

public class Curator implements TalkToNpcExecutiveListener, TalkToNpcListener, InvUseOnNpcListener, InvUseOnNpcExecutiveListener {
	public boolean blockTalkToNpc(final Player player, final Npc npc) {
		if (npc.getID() == 39) {
			return true;
		}
		return false;
	}

	@Override
	public void onTalkToNpc(final Player p, final Npc n) {
		Functions.npcTalk(p, n, "Welcome to the museum of varrock");
		if (p.getInventory().hasItemId(53) && p.getInventory().hasItemId(54)) {
			if (p.getQuestStage(Constants.Quests.SHIELD_OF_ARRAV) == 5) {
				Functions.playerTalk(p, n,
						"I have retrieved the shield of Arrav and I would like to claim my reward");
				Functions.npcTalk(p, n, "The shield of Arrav?", "Let me see that");
				Functions.message(p, "The curator peers at the shield");
				Functions.npcTalk(p,
						n,
						"This is incredible",
						"That shield has been missing for about twenty five years",
						"Well give me the shield",
						"And I'll write you out a certificate",
						"Saying you have returned the shield",
						"So you can claim your reward from the king");
				Functions.playerTalk(
						p,
						n,
						"Can I have two certificates?",
						"I needed significant help from a friend to get the shield",
						"We'll split the reward");
				Functions.npcTalk(p, n, "Oh ok");
				Functions.message(p, "You hand over the shield parts");
				Functions.removeItem(p, 53, 1);
				Functions.removeItem(p, 54, 1);
				Functions.message(p, "The curator writes out two certificates");
				Functions.addItem(p, 61, 1);
				Functions.addItem(p, 61, 1);
				Functions.npcTalk(p, n, "Take these to the king",
						"And he'll pay you both handsomely");

				return;
			}
		}
		Menu defaultMenu = new Menu();
		defaultMenu.addOption(new Option("Have you any interesting news?") {
			@Override
			public void action() {
				Functions.npcTalk(p, n, "No, i'm only interested in old stuff");
			}
		});
		defaultMenu.addOption(new Option(
				"Do you know where I could find any treasure?") {
			@Override
			public void action() {
				Functions.npcTalk(p, n, "This museum is full of treasures");
				Functions.playerTalk(p, n, "No, I meant treasures for me");
				Functions.npcTalk(p, n, "Any treasures this museum knows about",
						"It aquires");
			}
		});
		defaultMenu.showMenu(p);
	}

	@Override
	public boolean blockInvUseOnNpc(Player p, Npc n, Item item) {
		if(n.getID() == 39 && (item.getID() == DigsiteExaminer.UNSTAMPED_LETTER
				|| item.getID() == DigsiteExaminer.LEVEL_1_CERTIFICATE
				|| item.getID() == DigsiteExaminer.LEVEL_2_CERTIFICATE
				|| item.getID() == DigsiteExaminer.LEVEL_3_CERTIFICATE)) {
			return true;
		}
		return false;
	}

	@Override
	public void onInvUseOnNpc(Player p, Npc n, Item item) {
		if(n.getID() == 39) {
			if(item.getID() == DigsiteExaminer.UNSTAMPED_LETTER) {
				Functions.playerTalk(p, n, "I have been given this by the examiner at the digsite",
						"Can you stamp this for me ?");
				Functions.npcTalk(p, n, "What have we here ?",
						"A letter of recommendation indeed",
						"Normally I wouldn't do this",
						"But in this instance I don't see why not",
						"There you go, good luck student...");
				Functions.removeItem(p, DigsiteExaminer.UNSTAMPED_LETTER, 1);
				Functions.addItem(p, DigsiteExaminer.STAMPED_LETTER, 1);
				Functions.npcTalk(p, n, "Be sure to come back and show me your certificates",
						"I would like to see how you get on");
				Functions.playerTalk(p, n, "Okay, I will, thanks, see you later");
			}
			if(item.getID() == DigsiteExaminer.LEVEL_1_CERTIFICATE) {
				Functions.playerTalk(p, n, "Look what I have been awarded");
				Functions.removeItem(p, DigsiteExaminer.LEVEL_1_CERTIFICATE, 1);
				Functions.npcTalk(p, n, "Well that's great, well done",
						"I'll take that for safekeeping",
						"Come and tell me when you are the next level");
			}
			if(item.getID() == DigsiteExaminer.LEVEL_2_CERTIFICATE) {
				Functions.npcTalk(p, n, "Excellent work!");
				Functions.removeItem(p, DigsiteExaminer.LEVEL_2_CERTIFICATE, 1);
				Functions.npcTalk(p, n, "I'll take that for safekeeping",
						"Remember to come and see me when you have graduated");
			}
			if(item.getID() == DigsiteExaminer.LEVEL_3_CERTIFICATE) {
				Functions.playerTalk(p, n, "Look at this certificate, curator...");
				Functions.npcTalk(p, n, "Well well, a level 3 graduate!",
						"I'll keep your certificate safe for you",
						"I feel I must reward you for your work...",
						"What would you prefer, something to eat or drink ?");
				int menu = Functions.showMenu(p, n,
						"Something to eat please",
						"Something to drink please");
				if(menu == 0) {
					Functions.removeItem(p, DigsiteExaminer.LEVEL_3_CERTIFICATE, 1);
					Functions.npcTalk(p, n, "Very good, come and eat this cake I baked");
					Functions.playerTalk(p, n, "Yum, thanks!");
					Functions.addItem(p, 332, 1);
				} else if(menu == 1) {
					Functions.removeItem(p, DigsiteExaminer.LEVEL_3_CERTIFICATE, 1);
					Functions.npcTalk(p, n, "Certainly, have this...");
					Functions.addItem(p, 866, 1);
					Functions.playerTalk(p, n, "A cocktail?");
					Functions.npcTalk(p, n, "It's a new recipie from the gnome kingdom",
							"You'll like it I'm sure");
					Functions.playerTalk(p, n, "Cheers!");
					Functions.npcTalk(p, n, "Cheers!");
				}
			}
		}
	}
}
