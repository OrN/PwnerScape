package com.pwns.server.plugins.npcs.taverly;

import static com.pwns.server.plugins.Functions.npcTalk;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.menu.Menu;
import com.pwns.server.plugins.menu.Option;
import com.pwns.server.Constants;
import com.pwns.server.plugins.Functions;

public class Lady implements TalkToNpcExecutiveListener, TalkToNpcListener {

	@Override
	public void onTalkToNpc(final Player p, final Npc n) {
		Functions.npcTalk(p, n, "Good day to you " + p.isMale() != null ? "sir" : "madam");
		Menu defaultMenu = new Menu();
		defaultMenu.addOption(new Option("Who are you?") {
			@Override
			public void action() {
				Functions.npcTalk(p, n, "I am the lady of the lake");
			}
		});
		defaultMenu.addOption(new Option("Good day") {
			@Override
			public void action() {
				// NOTHING HAPPENS
			}
		});
		if (p.getQuestStage(Constants.Quests.MERLINS_CRYSTAL) == 3 || p.getQuestStage(Constants.Quests.MERLINS_CRYSTAL) == -1) {
			defaultMenu.addOption(new Option("I seek the sword Exalibur") {
				@Override
				public void action() {
					Functions.npcTalk(p,
							n,
							"Aye, I have that artifact in my possession",
							"Tis very valuable and not an artifact to be given away lightly",
							"I would want to give it away only to one who is worthy and good");
					Functions.playerTalk(p, n, "And how am I meant to prove that");
					Functions.npcTalk(p, n, "I will set a test for you",
							"First I need you to travel to Port Sarim",
							"Then go to the upstairs room of the jeweller's shop there");
					Functions.playerTalk(p, n, "Ok that seems easy enough");
					p.getCache().store("lady_test", true);
				}
			});
		}
		defaultMenu.showMenu(p);
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 283;
	}

}