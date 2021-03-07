package com.pwns.server.plugins.npcs.seers;

import static com.pwns.server.plugins.Functions.hasItem;
import static com.pwns.server.plugins.Functions.message;
import static com.pwns.server.plugins.Functions.npcTalk;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.menu.Menu;
import com.pwns.server.plugins.menu.Option;
import com.pwns.server.plugins.Functions;

public final class SeersBartender implements TalkToNpcExecutiveListener,
        TalkToNpcListener {

	@Override
	public void onTalkToNpc(final Player p, final Npc n) {
		if (n.getID() == 306) {
			Functions.npcTalk(p, n, "Good morning, what would you like?");
			Menu defaultMenu = new Menu();
			defaultMenu.addOption(new Option("What do you have?") {
				@Override
				public void action() {
					Functions.npcTalk(p, n, "Well we have a beer",
							"Or if you want some food, we have our home made stew and meat pies");
					Menu def = new Menu();
					def.addOption(new Option("Beer please") {
						@Override
						public void action() {
							Functions.npcTalk(p, n, "one beer coming up",
									"Ok, that'll be two coins");
							if (Functions.hasItem(p, 10, 2)) {
								p.message("You buy a pint of beer");
								Functions.addItem(p, 193, 1);
								p.getInventory().remove(10, 18);
							} else {
								Functions.playerTalk(p, n,
										"Oh dear. I don't seem to have enough money");
							}

						}
					});
					def.addOption(new Option("I'll try the meat pie") {
						@Override
						public void action() {
							Functions.npcTalk(p, n, "Ok, that'll be 16 gold");
							if (Functions.hasItem(p, 10, 16)) {
								p.message("You buy a nice hot meat pie");
								Functions.addItem(p, 259, 1);
								p.getInventory().remove(10, 18);
							} else {
								Functions.playerTalk(p, n,
										"Oh dear. I don't seem to have enough money");
							}

						}
					});
					def.addOption(new Option("Could I have some stew please") {
						@Override
						public void action() {
							Functions.npcTalk(p, n,
									"A bowl of stew, that'll be 20 gold please");
							if (Functions.hasItem(p, 10, 16)) {
								p.message("You buy a bowl of home made stew");
								Functions.addItem(p, 346, 1);
								p.getInventory().remove(10, 18);
							} else {
								Functions.playerTalk(p, n,
										"Oh dear. I don't seem to have enough money");
							}

						}
					});
					def.addOption(new Option(
							"I don't really want anything thanks") {
						@Override
						public void action() {
						}
					});
					def.showMenu(p);
				}
			});
			defaultMenu.addOption(new Option("Beer please") {
				@Override
				public void action() {
					Functions.npcTalk(p, n, "one beer coming up",
							"Ok, that'll be two coins");
					if (Functions.hasItem(p, 10, 2)) {
						p.message("You buy a pint of beer");
						Functions.addItem(p, 193, 1);
						p.getInventory().remove(10, 18);
					} else {
						Functions.playerTalk(p, n,
								"Oh dear. I don't seem to have enough money");
					}
				}
			});
			if (p.getCache().hasKey("barcrawl")
					&& !p.getCache().hasKey("barfive")) {
				defaultMenu.addOption(new Option(
						"I'm doing Alfred Grimhand's barcrawl") {
					@Override
					public void action() {
						Functions.npcTalk(p,
								n,
								"Oh you're a barbarian then",
								"Now which of these was the barrels contained the liverbane ale?",
								"That'll be 18 coins please");
						if (Functions.hasItem(p, 10, 18)) {
							p.getInventory().remove(10, 18);
							Functions.message(p,
									"The bartender gives you a glass of liverbane ale",
									"You gulp it down",
									"The room seems to be swaying",
									"The bartender scrawls his signature on your card");
							p.getCache().store("barfive", true);
						} else {
							Functions.playerTalk(p, n, "I don't have 18 coins right now");
						}
					}
				});
			}
			defaultMenu.addOption(new Option(
					"I don't really want anything thanks") {
				@Override
				public void action() {
				}
			});
			defaultMenu.showMenu(p);
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if (n.getID() == 306) {
			return true;
		}
		return false;
	}

}
