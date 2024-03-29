package com.pwns.server.plugins.npcs.falador;

import static com.pwns.server.plugins.Functions.hasItem;
import static com.pwns.server.plugins.Functions.message;
import static com.pwns.server.plugins.Functions.npcTalk;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.menu.Menu;
import com.pwns.server.plugins.menu.Option;
import com.pwns.server.Constants;
import com.pwns.server.plugins.Functions;

public class Bartender implements TalkToNpcExecutiveListener, TalkToNpcListener {

	@Override
	public void onTalkToNpc(final Player p, final Npc n) {
		Menu defaultMenu = new Menu();
		defaultMenu.addOption(new Option("Could i buy a beer please?") {
			@Override
			public void action() {
				Functions.npcTalk(p, n, "Sure that will be 2 gold coins please");
				if (p.getInventory().remove(10, 2) > -1) {
					Functions.addItem(p, 193, 1);
				} else {
					p.message("You dont have enough coins for the beer");
				}
			}
		});
		if (p.getQuestStage(Constants.Quests.GOBLIN_DIPLOMACY) == 0) {
			defaultMenu.addOption(new Option(
					"Not very busy in here today is it?") {
				@Override
				public void action() {
					Functions.npcTalk(p,
							n,
							"No it was earlier",
							"There was a guy in here saying the goblins up by the mountain",
							"Are arguing again",
							"Of all things about the colour of their armour",
							"Knowing the goblins,it could easily turn into a full blown war",
							"Which wouldn't be good",
							"Goblin wars make such a mess of the countryside");
					Functions.playerTalk(p, n,
							"Well if I have time I'll see if i can go and knock some sense into them");
					p.updateQuestStage(Constants.Quests.GOBLIN_DIPLOMACY, 1); // remember
																	// quest
																	// starts
																	// here.
				}
			});
		} else if (p.getQuestStage(Constants.Quests.GOBLIN_DIPLOMACY) >= 1
				|| p.getQuestStage(Constants.Quests.GOBLIN_DIPLOMACY) == -1) { // TODO
			defaultMenu.addOption(new Option(
					"Have you heard any more rumours in here?") {
				@Override
				public void action() {
					Functions.npcTalk(p, n, "No it hasn't been very busy lately");
				}
			});
		}
		if (p.getCache().hasKey("barcrawl") && !p.getCache().hasKey("barsix")) {
			defaultMenu.addOption(new Option(
					"I'm doing Alfred Grimhand's barcrawl") {
				@Override
				public void action() {
					Functions.npcTalk(p, n, "Are you sure you look a bit skinny for that");
					Functions.playerTalk(p, n,
							"Just give me whatever drink I need to drink here");
					Functions.npcTalk(p, n,
							"Ok one black skull ale coming up, 8 coins please");
					if (Functions.hasItem(p, 10, 8)) {
						Functions.message(p, "You buy a black skull ale",
								"You drink your black skull ale",
								"Your vision blurs",
								"The bartender signs your card");
						p.getCache().store("barsix", true);
						Functions.playerTalk(p, n, "hiccup", "hiccup");
					} else {
						Functions.playerTalk(p, n, "I don't have 8 coins right now");
					}
				}
			});
		}
		defaultMenu.showMenu(p);
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 150;
	}

}