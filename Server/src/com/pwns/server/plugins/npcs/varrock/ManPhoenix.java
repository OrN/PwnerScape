package com.pwns.server.plugins.npcs.varrock;

import static com.pwns.server.plugins.Functions.hasItem;
import static com.pwns.server.plugins.Functions.message;
import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.removeItem;

import com.pwns.server.Constants;
import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.menu.Menu;
import com.pwns.server.plugins.menu.Option;
import com.pwns.server.plugins.quests.free.ShieldOfArrav;
import com.pwns.server.plugins.Functions;

public class ManPhoenix implements TalkToNpcExecutiveListener,
        TalkToNpcListener {

	@Override
	public void onTalkToNpc(final Player p, final Npc n) {
		if(p.getCache().hasKey("arrav_gang") && p.getQuestStage(Constants.Quests.HEROS_QUEST) >= 1 && p.getCache().getInt("arrav_gang") == ShieldOfArrav.PHOENIX_GANG) {
			if(Functions.hasItem(p, 585)) {
				Functions.playerTalk(p,n, "I have retrieved a candlestick");
				Functions.npcTalk(p,n, "Hmm not a bad job",
						"Let's see it, make sure it's genuine");
				p.message("You hand Straven the candlestick");
				Functions.removeItem(p, 585, 1);
				Functions.playerTalk(p,n, "So is this enough to get me a master thieves armband?");
				Functions.npcTalk(p,n, "Hmm I dunno",
						"I suppose I'm in a generous mood today");
				Functions.playerTalk(p,n, "Straven hands you a master thief armband");
				Functions.addItem(p, 586, 1);
				return;
			}
			Functions.npcTalk(p, n, "How would i go about getting a master thieves armband?",
			"Ooh tricky stuff, took me years to get that rank",
					"Well what some of aspiring thieves in our gang are working on right now",
					"Is to steal some very valuable rare candlesticks",
					"From scarface Pete - the pirate leader on Karamja",
					"His security is good enough and the target valuable enough",
					"That might be enough to get you the rank",
					"Go talk to our man Alfonse the waiter in the shrimp and parrot",
					"Use the secret word gherkin to show you're one of us");
			p.getCache().store("pheonix_mission", true);
			p.getCache().store("pheonix_alf", true);
			return;
		}
		if (!Functions.hasItem(p, 48) && p.getQuestStage(Constants.Quests.SHIELD_OF_ARRAV) == 5) {
			Functions.npcTalk(p, n, "Greetings fellow gang member");
			Functions.playerTalk(p, n, "I have lost the key you gave me");
			Functions.npcTalk(p, n, "You need to be more careful",
					"We dont want that key falling into the wrong hands",
					"Ah well");
			Functions.message(p, "Straven hands you a key");
			Functions.addItem(p, 48, 1);
			Functions.npcTalk(p, n, "Have this spare");
			return;
		}
		if (p.getInventory().hasItemId(49)
				&& p.getQuestStage(Constants.Quests.SHIELD_OF_ARRAV) == 4) { // isnt this
			// going to
			// be 4?ya
			Functions.npcTalk(p, n, "Hows your little mission going?");
			Functions.playerTalk(p, n, "I have the intelligence report");
			Functions.npcTalk(p, n, "Lets see it then");
			Functions.message(p, "You hand over the report");
			Functions.removeItem(p, 49, 1);
			Functions.npcTalk(p, n, "Yes this is very good",
					"Ok you can join the phoenix gang",
					"I am Straven, one of the gang leaders");
			Functions.playerTalk(p, n, "Nice to meet you");
			Functions.npcTalk(p, n, "Here is a key");
			Functions.message(p, "Straven hands you a key");
			Functions.addItem(p, 48, 1);
			Functions.npcTalk(p, n, "It will let you enter our weapon supply area",
					"Round the front of this building");
			p.updateQuestStage(Constants.Quests.SHIELD_OF_ARRAV, 5);
			return;
		} else if (p.getQuestStage(Constants.Quests.SHIELD_OF_ARRAV) == 4) {
			Functions.npcTalk(p, n, "Hows your little mission going?");
			Functions.playerTalk(p, n, "I haven't managed to find the report yet");
			Functions.npcTalk(p,
					n,
					"You need to kill jonny the beard, who should be in the blue moon inn.",
					"...I would guess. Not being a member of the phoenix gang and all.");
		}
		if (p.getQuestStage(Constants.Quests.SHIELD_OF_ARRAV) <= 3) {
			defaultConverstation(p, n);
			return;
		}
		if (p.getQuestStage(Constants.Quests.SHIELD_OF_ARRAV) == 5
				|| p.getQuestStage(Constants.Quests.SHIELD_OF_ARRAV) == -1 || p.getQuestStage(Constants.Quests.HEROS_QUEST) == -1) {
			memberOfPhoenixConverstation(p, n);
			return;
		}

	} // what is incomplete? the task the guy gives. = stage 4,= missing

	private void memberOfPhoenixConverstation(final Player p, final Npc n) {
		Menu defaultMenu = new Menu();
		Functions.npcTalk(p, n, "Greetings fellow gang member");
		defaultMenu.addOption(new Option(
				"I've heard you've got some cool treasures in this place") {
			public void action() {
				Functions.npcTalk(p, n,
						"Oh yeah, we've stolen some cool stuff in our time",
						"The candlesticks down here",
						"Were quite a challenge to get out the palace");
				Functions.playerTalk(p, n, "And the shield of arrav",
						"I heard you got that");
				Functions.npcTalk(p, n, "Hmm", "That was a while ago",
						"We don't even have the shield anymore",
						"About 5 years ago",
						"We had a massive fight in our gang",
						"The shield got broken in half during the fight",
						"Shortly after the fight", "Some gang members decided",
						"They didn't want to be part of our gang anymore",
						"So they split off to form their own gang",
						"The black arm gang", "On their way out",
						"They looted what treasure they could from us",
						"Which included one of the halves of the shield",
						"We've been rivals with the black arms ever since");
			}
		});
		defaultMenu.addOption(new Option(
				"Any suggestions for where I can go thieving?") {
			public void action() {
				Functions.npcTalk(p, n, "You can always try he market",
						"Lots of opportunity there");
			}
		});
		defaultMenu.addOption(new Option("Where's the Blackarm gang hideout?") {
			public void action() {
				Functions.playerTalk(p, n, "I wanna go sabotage em");
				Functions.npcTalk(p, n, "That would be a little tricky",
						"Their security is pretty good",
						"Not as good as ours obviously", "But still good",
						"If you really want to go there",
						"It is in the alleyway",
						"To the west as you come in the south gate",
						"One of our operatives is often near the alley",
						"A red haired tramp",
						"He may be able to give you some ideas");
				Functions.playerTalk(p, n, "Thanks for your help");
			}
		});
		defaultMenu.showMenu(p);
	}

	private void defaultConverstation(final Player p, final Npc n) {
		Menu defaultMenu = new Menu();
		Functions.playerTalk(p, n, "What's through that door?");
		Functions.npcTalk(p,
				n,
				"Heh you can't go in there",
				"Only authorised personnel of the VTAM corporation are allowed beyond this point");
		defaultMenu.addOption(new Option(
				"How do I get a job with the VTAM Corporation?") {
			public void action() {
				Functions.npcTalk(p, n, "Get a copy of the Varrock Herald",
						"If we have any positions right now",
						"They'll be advertised in there");
			}
		});
		defaultMenu.addOption(new Option("Why not?") {
			public void action() {
				Functions.npcTalk(p, n, "Sorry that is classified information");
			}
		});
		if (p.getQuestStage(Constants.Quests.SHIELD_OF_ARRAV) == 3) {
			defaultMenu.addOption(new Option("I know who you are") {
				public void action() {
					Functions.npcTalk(p, n, "I see", "Carry on");
					Functions.playerTalk(p, n,
							"This is the headquarters of the Phoenix Gang",
							"The most powerful crime gang this city  has seen");
					Functions.npcTalk(p, n, "And supposing we were this crime gang",
							"What would you want with us?");
					new Menu().addOptions(
							new Option("I'd like to offer you my services") {
								public void action() {
									Functions.npcTalk(p,
											n,
											"You mean you'd like to join the phoenix gang?",
											"Well the phoenix gang doesn't let people join just like that",
											"You can't be too careful, you understand",
											"Generally someone has to prove their loyalty before they can join");
									Functions.playerTalk(p, n,
											"How would I go about this?");
									Functions.npcTalk(p,
											n,
											"Let me think",
											"I have an idea",
											"A rival gang of ours",
											"Called the black arm gang",
											"Is meant to be meeting their contact from Port Sarim today",
											"In the blue moon inn",
											"By the south entrance to this city",
											"The name of the contact is Johnny the beard",
											"Kill him and bring back his intelligence report");
									p.getCache().set("arrav_gang",
											ShieldOfArrav.PHOENIX_GANG);
									p.updateQuestStage(Constants.Quests.SHIELD_OF_ARRAV, 4);
									Functions.playerTalk(p, n, "Ok, I'll get on it");
								}
							},
							new Option(
									"I want nothing, I was just making sure you were them") {
								@Override
								public void action() {
									Functions.npcTalk(p, n, "Well stop wasting my time");
								}

							}).showMenu(p);

				}
			});
		}
		defaultMenu.showMenu(p);
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 24;
	}
}
