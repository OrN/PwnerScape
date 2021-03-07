package com.pwns.server.plugins.npcs.varrock;

import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.sleep;

import com.pwns.server.Constants;
import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.menu.Menu;
import com.pwns.server.plugins.menu.Option;
import com.pwns.server.plugins.Functions;

public final class Reldo implements TalkToNpcListener,
        TalkToNpcExecutiveListener {
	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 20;
	}

	/**
	 * Man, this is the whole reldo with shield of arrav. dont tell me that this
	 * is bad choice.
	 */
	@Override
	public void onTalkToNpc(final Player p, final Npc n) {
		Menu defaultMenu = new Menu();
		if (p.getCache().hasKey("read_arrav")
				&& p.getQuestStage(Constants.Quests.SHIELD_OF_ARRAV) == 1) {
			Functions.playerTalk(p, n, "Ok I've read the book",
					"Do you know where I can find the Phoenix Gang");
			Functions.npcTalk(p, n, "No I don't",
					"I think I know someone who will though",
					"Talk to Baraek, the fur trader in the market place",
					"I've heard he has connections with the Phoenix Gang");
			Functions.playerTalk(p, n, "Thanks, I'll try that");
			p.updateQuestStage(Constants.Quests.SHIELD_OF_ARRAV, 2);
			return;
		}
		Functions.playerTalk(p, n, "Hello");
		Functions.npcTalk(p, n, "Hello stranger");
		if (p.getQuestStage(Constants.Quests.SHIELD_OF_ARRAV) == 0) {
			defaultMenu.addOption(new Option("I'm in search of quest.") {
				@Override
				public void action() {
					Functions.npcTalk(p, n, "I don't think there's any here");
					Functions.sleep(600);
					Functions.npcTalk(p, n, "Let me think actually",
							"If you look in a book",
							"called the shield of Arrav",
							"You'll find a quest in there",
							"I'm not sure where the book is mind you",
							"I'm sure it's somewhere in here");
					Functions.playerTalk(p, n, "Thankyou");
					p.updateQuestStage(Constants.Quests.SHIELD_OF_ARRAV, 1);
				}
			});
		}
		defaultMenu.addOption(new Option("Do you have anything to trade") {
			@Override
			public void action() {
				Functions.npcTalk(p, n, "No sorry. I'm not the trading type");
				Functions.playerTalk(p, n, "ah well");
			}
		});
		defaultMenu.addOption(new Option("What do you do?") {
			@Override
			public void action() {
				Functions.npcTalk(p, n, "I'm the palace librarian");
				Functions.playerTalk(p, n, "Ah that's why you're in the library then");
				Functions.npcTalk(p, n, "Yes",
						"Though I might be in here even if I didn't work here",
						"I like reading");
			}
		});
		if (p.getQuestStage(Constants.Quests.THE_KNIGHTS_SWORD) == 1) {
			defaultMenu.addOption(new Option(
					"What do you know about the incando dwarves?") {
				@Override
				public void action() {
					Functions.npcTalk(p,
							n,
							"The incando dwarves, you say?",
							"They were the world's most skilled smiths about a hundred years ago",
							"They used secret knowledge",
							"Which they passed down from generation to generation",
							"Unfortunatly about a century ago the once thriving race",
							"Was wiped out during the barbarian invasions of that time");
					Functions.playerTalk(p, n, "So are there any incando left at all?");
					Functions.npcTalk(p,
							n,
							"A few of them survived",
							"But with the bulk of their population destroyed",
							"Their numbers have dwindled even further",
							"Last i knew there were a couple living in asgarnia",
							"Near the cliffs on the asgarnian southern peninsula",
							"They tend to keep to themselves",
							"They don't tend to tell people that they're the descendants of the incando",
							"Which is why people think that the tribe has died out totally",
							"You may have more luck talking to them if you bring them some red berry pie",
							"They really like red berry pie");
					Functions.playerTalk(p, n, "Thank you");
					Functions.npcTalk(p, n, "You're welcome");
					p.updateQuestStage(Constants.Quests.THE_KNIGHTS_SWORD, 2);
				}
			});
		}

		defaultMenu.showMenu(p);
	}
}
