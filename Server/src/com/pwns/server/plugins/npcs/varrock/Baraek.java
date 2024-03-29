package com.pwns.server.plugins.npcs.varrock;

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

public final class Baraek implements TalkToNpcExecutiveListener,
        TalkToNpcListener {

	@Override
	public void onTalkToNpc(final Player p, final Npc n) {
		Menu defaultMenu = new Menu();
		defaultMenu.addOption(new Option("Can you sell me some furs?") {
			@Override
			public void action() {
				Functions.npcTalk(p, n, "Yeah sure they're 20 gold coins a piece");
				new Menu().addOptions(new Option("Yeah ok here you go") {
					@Override
					public void action() {
						if (p.getInventory().remove(10, 20) > -1) {
							p.message(
									"You buy a fur from Baraek");
							p.getInventory().add(new Item(146));
						} else {
							Functions.playerTalk(p, n,
									"oh dear, i don't have enough coins");
						}
					}
				}, new Option("20 gold coins that's an outrage") {
					@Override
					public void action() {
						Functions.npcTalk(p, n, "Okay I'll go down to 18");
						new Menu().addOptions(new Option("Ok here you go") {
							@Override
							public void action() {
								if (p.getInventory().remove(10, 18) > -1) {
									p.message("You buy a fur from Baraek");
									p.getInventory().add(new Item(146));
								} else {
									Functions.playerTalk(p, n,
											"oh dear, i don't have enough coins");
								}
							}
						}, new Option("No thanks, I'll leave it") {
							@Override
							public void action() {
								Functions.npcTalk(p, n, "Okay, it's your loss.");
							}
						}).showMenu(p);
					}
				}).showMenu(p);
			}
		});
		defaultMenu.addOption(new Option("Hello I am in search of a quest") {
			@Override
			public void action() {
				Functions.npcTalk(p, n,
						"sorry kiddo, i'm a fur trader not a damsel in distress");
			}
		});
		if (p.getInventory().hasItemId(146)) {
			defaultMenu.addOption(new Option("Would you like to buy my fur?") {
				@Override
				public void action() {
					Functions.npcTalk(p, n, "Lets have a look at it");
					Functions.message(p, "Baraek examines a fur");
					Functions.npcTalk(p, n, "it's not in the best of condition",
							"i guess i could give 12 coins to take it off your hands");
					new Menu().addOptions(new Option("Yeah that'll do.") {
						@Override
						public void action() {
							Functions.message(p,"You give Baraek a fur",
									"And he gives you twelve coins");
							Functions.removeItem(p, 146, 1);
							Functions.addItem(p, 10, 12);
						}
					}, new Option("I think I'll keep hold of it actually") {
						@Override
						public void action() {
							Functions.npcTalk(p, n, "oh ok", "didn't want it anyway");
						}
					}).showMenu(p);
				}
			});
		}
		if (p.getQuestStage(Constants.Quests.SHIELD_OF_ARRAV) == 2) {
			defaultMenu.addOption(new Option(
					"Can you tell me where I can find the phoenix gang?") {
				@Override
				public void action() {
					Functions.npcTalk(p, n, "Sh Sh, not so loud",
							"You don't want to get me in trouble");
					Functions.playerTalk(p, n, "So do you know where they are?");
					Functions.npcTalk(p,
							n,
							"I may do",
							"Though I don't want to get into trouble for revealing their hideout",
							"Now if I was say 20 gold coins richer",
							"I may happen to be more inclined to take that sort of risk");
					new Menu().addOptions(
							new Option("Okay have 20 gold coins") {
								@Override
								public void action() {
									Functions.removeItem(p, 10, 20);
									Functions.npcTalk(p,
											n,
											"Cheers",
											"Ok to get to the gang hideout",
											"After entering Varrock through the south gate",
											"If you take the first turning east",
											"Somewhere along there is an alleyway to the south",
											"The door at the end of there is the entrance to the phoenix gang",
											"They're operating there under the name of VTAM corporation",
											"Be careful",
											"The phoenix gang ain't the types to be messed with");
									Functions.playerTalk(p, n, "Thanks");
									p.updateQuestStage(
											Constants.Quests.SHIELD_OF_ARRAV, 3);
								}
							},
							new Option("No I don't like things like bribery") {
								public void action() {
									Functions.npcTalk(p,
											n,
											"Heh, If you wanna deal with the phoenix gang",
											"They're involved in much worse than a bit of bribery");
								}
							},
							new Option(
									"Yes, I'd like to be 20 gold richer too.") {
								@Override
								public void action() {
								}
							}).showMenu(p);
				}
			});
		}
		defaultMenu.showMenu(p);
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 26;
	}

}
