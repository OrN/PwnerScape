package com.pwns.server.plugins.minigames.fishingtrawler;

import static com.pwns.server.plugins.Functions.message;
import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.model.world.World;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.menu.Menu;
import com.pwns.server.plugins.menu.Option;
import com.pwns.server.plugins.Functions;

public class Murphy implements TalkToNpcListener, TalkToNpcExecutiveListener {

	/**
	 * IMPORTANT NOTES:
	 * 
	 * START EAST: 272, 741 START WEST: 320, 741 GO UNDER EAST: 251, 730 GO
	 * UNDER WEST: 296, 729 - NPC: 734 FAIL - AFTER GO UNDER EAST: 254, 759
	 * (SHARED) FAIL - AFTER GO UNDER WEST AND/OR QUIT MINI-GAME: 302, 759 GO
	 * BACK FROM FAIL LOCATION: 550, 711
	 */

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 733 || n.getID() == 734 || n.getID() == 739;
	}

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		if (n.getID() == 733) { // Murphy on land
			if(p.isIronMan(1) || p.isIronMan(2) || p.isIronMan(3)) {
				p.message("As an Iron Man, you cannot use the Trawler.");
				return;
			}
			if (!p.getCache().hasKey("fishingtrawler")) {
				Functions.playerTalk(p, n, "good day to you sir");
				Functions.npcTalk(p, n, "well hello my brave adventurer");
				Functions.playerTalk(p, n, "what are you up to?");
				Functions.npcTalk(p, n, "getting ready to go fishing of course");
				Functions.npcTalk(p, n, "there's no time to waste");
				Functions.npcTalk(p, n, "i've got all the supplies i need from the shop at the end of the pier");
				Functions.npcTalk(p, n, "they sell good rope, although their bailing buckets aren't too effective");
				showStartOption(p, n, true, true, true);
			} else {
				Functions.playerTalk(p, n, "hello again murphy");
				Functions.npcTalk(p, n, "good day to you land lover");
				if(p.getCache().hasKey("fishing_trawler_reward")) {
					Functions.npcTalk(p,n, "It looks like your net is full from last trip");
					return;
				}
				Functions.npcTalk(p, n, "fancy hitting the high seas again?");
				int option = Functions.showMenu(p, n, "no thanks, i still feel ill from last time", "yes, lets do it");
				if (option == 0) {
					Functions.npcTalk(p, n, "hah..softy");
				} else if (option == 1) {
					letsGo(p, n);
				}
			}
		} else if (n.getID() == 734) {// Murphy on the boat.
			onship(n, p);
		} else if(n.getID() == 739) {
			Functions.npcTalk(p,n, "did you change your mind?");
			int opt = Functions.showMenu(p,n, "Yes, I want out", "No");
			if(opt == 0) {
				World.getWorld().getFishingTrawler().getWaitingShip().removePlayer(p);
				
			}
		}
	}

	private void showStartOption(Player p, Npc n, boolean b, boolean c, boolean d) {
		Menu menu = new Menu();
		if (b) {
			menu.addOption(new Option("what fish do you catch?") {
				@Override
				public void action() {
					Functions.npcTalk(p, n, "i get all sorts, anything that lies on the sea bed");
					Functions.npcTalk(p, n, "you never know what you're going to get until...");
					Functions.npcTalk(p, n, "...you pull up the net");
					showStartOption(p, n, false, true, true);
				}
			});
		}
		if (c) {
			menu.addOption(new Option("your boat doesn't look too safe") {
				@Override
				public void action() {
					Functions.npcTalk(p, n, "that's because it's not, the dawn thing's full of holes");
					Functions.playerTalk(p, n, "oh, so i suppose you can't go out for a while");
					Functions.npcTalk(p, n, "oh no, i don't let a few holes stop an experienced sailor like me");
					Functions.npcTalk(p, n, "i could sail these seas in a barrel	");
					Functions.npcTalk(p, n, "i'll be going out soon enough");
					showStartOption(p, n, true, false, true);
				}
			});
		}
		if (d) {
			menu.addOption(new Option("could i help?") {
				@Override
				public void action() {
					Functions.npcTalk(p, n, "well of course you can");
					Functions.npcTalk(p, n, "i'll warn you though, the seas are merciless");
					Functions.npcTalk(p, n, "and with out fishing experience you won't catch much");
					Functions.message(p, "you need a fishing level of 15 or above to catch any fish on the trawler");
					Functions.npcTalk(p, n, "on occasions the net rip's, so you'll need some rope to repair it");
					Functions.playerTalk(p, n, "rope...ok");
					Functions.npcTalk(p, n, "there's also a slight problem with leaks");
					Functions.playerTalk(p, n, "leaks!");
					Functions.npcTalk(p, n, "nothing some swamp paste won't fix");
					Functions.playerTalk(p, n, "swamp paste?");
					Functions.npcTalk(p, n, "oh, and one more thing...");
					Functions.npcTalk(p, n, "..i hope you're a good swimmer");
					int gooption = Functions.showMenu(p, n, "actually, i think i'll leave it", "i'll be fine, lets go",
							"what's swamp paste?");
					switch (gooption) {
					case 0:
						break;
					case 1:
						Functions.playerTalk(p, n, "i'll be fine, lets go");
						letsGo(p, n);
						break;
					case 2:
						Functions.npcTalk(p, n, "swamp tar mixed with flour...");
						Functions.npcTalk(p, n, "...which is then heated over a fire");
						Functions.playerTalk(p, n, "where can i find swamp tar?");
						Functions.npcTalk(p, n, "unfortunately the only supply of swamp tar is in the swamps below lumbridge");
						break;
					}
				}
			});
		}
		menu.showMenu(p);
	}

	protected void letsGo(Player p, Npc n) {
		Functions.npcTalk(p, n, "good stuff, jump aboard");
		Functions.npcTalk(p, n, "ok m hearty, keep your eys pealed");
		Functions.npcTalk(p, n, "i need you to clog up those holes quick time");
		Functions.playerTalk(p, n, "i'm ready and waiting");
		p.getCache().store("fishingtrawler", true);
		World.getWorld().getFishingTrawler().getWaitingShip().addPlayer(p);
		
//		npcTalk(p, n, "would you like to sail east or west?");
//		Menu goMenu = new Menu();
//		goMenu.addOptions(new Option("east please") {
//			@Override
//			public void action() {
//				npcTalk(p, n, "good stuff, jump aboard");
//				npcTalk(p, n, "ok m hearty, keep your eys pealed");
//				npcTalk(p, n, "i need you to clog up those holes quick time");
//				playerTalk(p, n, "i'm ready and waiting");
//				p.teleport(272, 741, true);
//			}
//		}, new Option("west please") {
//			@Override
//			public void action() {
//				npcTalk(p, n, "good stuff, jump aboard");
//				npcTalk(p, n, "ok m hearty, keep your eys pealed");
//				npcTalk(p, n, "i need you to clog up those holes quick time");
//				playerTalk(p, n, "i'm ready and waiting");
//				p.teleport(320, 741, true);
//			}
//		});
//		goMenu.showMenu(p);
	}

	void onship(Npc n, Player p) {
		Functions.npcTalk(p, n, "whoooahh sailor");
		int option = Functions.showMenu(p, n, "i've had enough,  take me back", "how you doing murphy?");
		if (option == 0) {
			Functions.npcTalk(p, n, "haa .. the soft land lovers lost there see legs have they?");
			Functions.playerTalk(p, n, "something like that");
			Functions.npcTalk(p, n, "we're too far out now, it'd be dangerous");
			option = Functions.showMenu(p, n, "I insist murphy, take me back", "Ok then murphy, just keep us afloat");
			if (option == 0) {
				Functions.npcTalk(p, n, "ok, ok, i'll try, but don't say i didn't warn you");
				Functions.message(p, 1900, "murphy sharply turns the large ship", "the boats gone under", "you're lost at sea!");
				World.getWorld().getFishingTrawler().quitPlayer(p);
			}
		}
		if (option == 1) {
			Functions.npcTalk(p, n, "get those fishey's");
		}
	}
}
