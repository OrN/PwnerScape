package com.pwns.server.plugins.npcs.draynor;

import static com.pwns.server.plugins.Functions.message;
import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.removeItem;

import com.pwns.server.Constants;
import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.menu.Menu;
import com.pwns.server.plugins.menu.Option;
import com.pwns.server.plugins.Functions;

public final class Ned implements TalkToNpcExecutiveListener, TalkToNpcListener {

	@Override
	public boolean blockTalkToNpc(final Player p, final Npc n) {
		return n.getID() == 124;
	}

	@Override
	public void onTalkToNpc(final Player p, final Npc n) {
		Functions.npcTalk(p, n, "Why hello there, me friends call me Ned");
		Functions.npcTalk(p, n, "I was a man of the sea, but its past me now");
		Functions.npcTalk(p, n, "Could I be making or selling you some Rope?");
		final Menu defaultMenu = new Menu();
		if (p.getQuestStage(Constants.Quests.DRAGON_SLAYER) == 2
				&& !p.getCache().hasKey("ned_hired")) {
			defaultMenu
					.addOption(new Option(
							"You're a sailor? Could you take me to the Isle of Crandor") {
						@Override
						public void action() {
							Functions.npcTalk(p, n, "Well I was a sailor");
							Functions.npcTalk(p, n,
									"I've not been able to get work at sea these days though");
							Functions.npcTalk(p, n, "They say I am too old");
							Functions.message(p, "There is a wistfull look in Ned's eyes");
							Functions.npcTalk(p, n, "I miss those days");
							Functions.npcTalk(p, n,
									"If you could get me a ship I would take you anywhere");
							if (p.getCache().hasKey("ship_fixed")) {
								Functions.playerTalk(p, n,
										"As it happens I do have a ship ready to sail");
								Functions.npcTalk(p, n, "That'd be grand, where is it");
								Functions.playerTalk(p, n,
										"It's called the Lumbridge Lady and it's docked in Port Sarim");
								Functions.npcTalk(p, n,
										"I'll go right over there and check her out then");
								Functions.npcTalk(p, n, "See you over there");
								p.getCache().store("ned_hired", true);
							} else {
								Functions.playerTalk(p, n,
										"I will work on finding a sea worthy ship then");
							}
						}
					});
		}
		defaultMenu.addOption(new Option("Yes, I would like some Rope") {
			@Override
			public void action() {
				Functions.npcTalk(p,
						n,
						"Well, I can sell you some rope for 15 coins",
						"Or i can be making you some if you gets me 4 balls of wool",
						"I strands them together i does, makes em strong");
				new Menu().addOptions(
						new Option("Okay, please sell me some Rope") {
							public void action() {
								if (p.getInventory().countId(10) <= 15) {
									p.message("You don't have enough coins to buy the rope");
								} else {
									p.message("You hand Ned 15 coins");
									Functions.npcTalk(p, n,
											"There you go, finest rope in runescape");
									p.getInventory().add(new Item(237, 1));
									p.getInventory().remove(10, 15);
									p.message("Ned hands you a coil of rope");
								}
							}
						},
						new Option("Thats a little more than I want to pay") {
							@Override
							public void action() {
								Functions.npcTalk(p,
										n,
										"Well, if you ever need some rope. Thats the price. Sorry",
										"An old sailor needs money for a little drop o rum.");
							}

						}, new Option("I will go and get some wool") {

							@Override
							public void action() {
								Functions.npcTalk(p, n, "Aye, you do that",
										"Remember, it takes 4 balls of wool to make strong rope");
							}

						}).showMenu(p);
			}
		});
		if (p.getQuestStage(Constants.Quests.PRINCE_ALI_RESCUE) == 2) {
			defaultMenu.addOption(new Option(
					"Ned, could you make other things from wool?") {
				@Override
				public void action() {
					Functions.npcTalk(p, n, "Well... Thats an interesting thought",
							"yes, I think I could do something",
							"Give me 3 balls of wool and I might be able to do it");
					if (p.getInventory().countId(207) >= 3) {
						new Menu()
								.addOptions(
										new Option(
												"I have that now. Please, make me a wig") {
											@Override
											public void action() {
												Functions.npcTalk(p, n,
														"Okay. I will have a go.");
												Functions.message(p,
														"You hand Ned 3 balls of wool",
														"Ned works with the wool. His hands move with a speed you couldn't imagine");
												Functions.removeItem(p, 207, 3);
												Functions.npcTalk(p, n,
														"Here you go, hows that for a quick effort? Not bad I think!");
												p.message("Ned gives you a pretty good wig");
												Functions.addItem(p, 245, 1);
												Functions.playerTalk(p, n,
														"Thanks Ned, theres more to you than meets the eye");
											}
										},
										new Option(
												"I will come back when I need you to make one") {

											@Override
											public void action() {
												Functions.npcTalk(p,
														n,
														"Well, it sounds like a challenge",
														"Come to me if you need one");
											}
										}).showMenu(p);
					} else {
						Functions.playerTalk(p, n,
								"great, I will get some. I think a wig would be useful");
					}
				}
			});
		}
		defaultMenu.addOption(new Option("No thanks Ned, I don't need any") {
			@Override
			public void action() {
				Functions.npcTalk(p, n, "Well, old neddy is always here if you do",
						"Tell your friends, i can always be using the business");
			}
		});
		defaultMenu.showMenu(p);
	}
}
