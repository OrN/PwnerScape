package com.pwns.server.plugins.npcs.draynor;

import static com.pwns.server.plugins.Functions.npcTalk;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.menu.Menu;
import com.pwns.server.plugins.menu.Option;
import com.pwns.server.Constants;
import com.pwns.server.plugins.Functions;

public class Klarense implements TalkToNpcExecutiveListener, TalkToNpcListener {

	@Override
	public void onTalkToNpc(final Player p, final Npc n) {
		if (!p.getCache().hasKey("owns_ship")) {
			defaultDialogue(p, n);
		} else {
			ownsShipDialogue(p, n);
		}
	}

	private void ownsShipDialogue(final Player p, final Npc n) {
		Menu defaultMenu = new Menu();
		defaultMenu.addOption(new Option(
				"So would you like to sail this ship to Crandor Isle for me?") {
			@Override
			public void action() {
				Functions.npcTalk(p, n, " No not me, I'm frightened of dragons");
			}
		});
		defaultMenu.addOption(new Option("So what needs fixing on this ship?") {
			@Override
			public void action() {
				Functions.playerTalk(p, n, "So what needs fixing on this ship?");
				Functions.npcTalk(p, n,
						" Well the big gaping hole in the hold is the main problem");
				Functions.npcTalk(p, n, " you'll need a few planks");
				Functions.npcTalk(p, n, " Hammered in with steel nails");
			}
		});
		defaultMenu.addOption(new Option(
				"What are you going to do now you don't have a ship?") {
			@Override
			public void action() {
				Functions.npcTalk(p, n, " Oh I'll be fine");
				Functions.npcTalk(p, n, " I've got work as Port Sarim's first life guard");
			}
		});
		defaultMenu.showMenu(p);
	}

	private void defaultDialogue(final Player p, final Npc n) {
		Functions.npcTalk(p, n,
				" You're interested in a trip on the Lumbridge Lady are you?");
		Functions.npcTalk(p, n,
				" I admit she looks fine, but she isn't seaworthy right now");
		Menu defaultMenu = new Menu();
		defaultMenu.addOption(new Option(
				"Do you know when she will be seaworthy?") {
			@Override
			public void action() {
				Functions.npcTalk(p, n, " No not really");
				Functions.npcTalk(p, n,
						" Port Sarim's shipbuilders aren't very efficient");
				Functions.npcTalk(p, n, " So it could be quite a while");
			}
		});
		if (p.getQuestStage(Constants.Quests.DRAGON_SLAYER) == 2) {
			defaultMenu.addOption(new Option(
					"Would you take me to Crandor Isle when it's ready?") {
				@Override
				public void action() {
					Functions.npcTalk(p, n, " Well even if I knew how to get there");
					Functions.npcTalk(p, n, " I wouldn't like to risk it");
					Functions.npcTalk(p, n,
							" Especially after to goin to all the effort of fixing the old girl up");
				}
			});
			defaultMenu.addOption(new Option("I don't suppose I could buy it") {
				@Override
				public void action() {
					Functions.npcTalk(p, n, " I guess you could");
					Functions.npcTalk(p, n,
							" I'm sure the work needed to do on it wouldn't be too expensive");
					Functions.npcTalk(p, n, " How does 2000 gold sound for a price?");
					new Menu()
							.addOptions(
									new Option("Yep sounds good") {
										@Override
										public void action() {
											if (p.getInventory().countId(10) >= 2000) {
												Functions.npcTalk(p, n,
														"Ok, she's all yours.");
												p.getCache().store("owns_ship",
														true);
												p.getInventory().remove(10,
														2000);
											}
										}
									},
									new Option(
											"I'm not paying that much for a broken boat") {
										@Override
										public void action() {
											Functions.npcTalk(p, n,
													" That's Ok, I didn't particularly want to sell anyway");
										}
									}).showMenu(p);
				}
			});
		}
		defaultMenu.addOption(new Option("Ah well, never mind") {
			@Override
			public void action() {
			}
		});
		defaultMenu.showMenu(p);
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getDef().getName().equals("Klarense");
	}

}
