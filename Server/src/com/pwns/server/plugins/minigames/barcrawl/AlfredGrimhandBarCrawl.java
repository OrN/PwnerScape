package com.pwns.server.plugins.minigames.barcrawl;

import static com.pwns.server.plugins.Functions.doGate;
import static com.pwns.server.plugins.Functions.hasItem;
import static com.pwns.server.plugins.Functions.message;
import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.removeItem;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.model.world.World;
import com.pwns.server.plugins.listeners.action.ObjectActionListener;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.ObjectActionExecutiveListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class AlfredGrimhandBarCrawl implements TalkToNpcListener,
        TalkToNpcExecutiveListener, ObjectActionListener,
        ObjectActionExecutiveListener {

	@Override
	public boolean blockObjectAction(GameObject obj, String command,
                                     Player player) {
		if (obj.getID() == 311 && obj.getX() == 494) {
			return true;
		}
		return false;
	}

	@Override
	public boolean blockTalkToNpc(final Player p, final Npc n) {
		if (n.getID() == 305) {
			return true;
		}
		return false;
	}

	@Override
	public void onObjectAction(final GameObject obj, String command, Player p) {
		if (obj.getID() == 311 && obj.getX() == 494) {
			Npc barbarian = World.getWorld().getNpc(305, 494, 500, 538, 550);
			if (barbarian != null) {
				if (p.getCache().hasKey("barcrawl_completed")) {
					Functions.doGate(p, obj);
					return;
				}
				if (p.getCache().hasKey("barcrawl")) {
					Functions.npcTalk(p, barbarian, "So hows the barcrawl coming along?");
					if (!Functions.hasItem(p, 668)) {
						int third = Functions.showMenu(
								p,
								barbarian,
								new String[] { "I've lost my barcrawl card",
										"Not to bad, my barcrawl card is in my bank now" });
						if (third == 0) {
							Functions.npcTalk(p, barbarian, "What are you like?",
									"You're gonna have to start all over now",
									"Here you go, have another barcrawl card");
							Functions.addItem(p, 668, 1);
							p.getCache().remove("barone");
							p.getCache().remove("bartwo");
							p.getCache().remove("barthree");
							p.getCache().remove("barfour");
							p.getCache().remove("barfive");
							p.getCache().remove("barsix");
						} else if (third == 1) {
							Functions.npcTalk(p, barbarian,
									"You need it with you when you are going on a barcrawl");
						}
						return;
					} else if (p.getCache().hasKey("barone")
							&& p.getCache().hasKey("bartwo")
							&& p.getCache().hasKey("barthree")
							&& p.getCache().hasKey("barfour")
							&& p.getCache().hasKey("barfive")
							&& p.getCache().hasKey("barsix") && Functions.hasItem(p, 668)) {
						Functions.playerTalk(p, barbarian,
								"I think I jusht about done them all, but I losht count");
						Functions.message(p, "You give the card to the barbarian");
						Functions.removeItem(p, 668, 1);
						Functions.npcTalk(p,
								barbarian,
								"Yep that seems fine",
								"I never learned to read, but you look like you've drunk plenty",
								"You can come in now");
						p.getCache().store("barcrawl_completed", true);
					} else {
						Functions.playerTalk(p, barbarian, "I haven't finished it yet");
						Functions.npcTalk(p, barbarian,
								"Well come back when you have, you lightweight");
					}
					return;
				}
				Functions.npcTalk(p, barbarian, "Oi whaddya want?");
				int first = Functions.showMenu(p, barbarian,
						new String[] { "I want to come through this gate",
								"I want some money" });
				if (first == 0) {
					Functions.npcTalk(p, barbarian, "Barbarian only",
							"Are you a barbarian?", "You don't look like one");
					int second = Functions.showMenu(
							p,
							barbarian,
							new String[] { "Hmm, yep you've got me there",
									"Looks can be deceiving, I am in fact a barbarian" });
					if (second == 0) {
						// NOTHING
					} else if (second == 1) {
						Functions.npcTalk(p,
								barbarian,
								"If you're a barbarian you need to be able to drink like one",
								"We barbarians like a good drink",
								"And I have the perfect challenge for you",
								"The Alfred Grimhand barcrawl",
								"First done by Alfred Grimhand");
						Functions.message(p, "The guard hands you a barcrawl card");
						Functions.addItem(p, 668, 1);
						Functions.npcTalk(p,
								barbarian,
								"Take that card to each of the bars named on it",
								"The bartenders all know what it means",
								"We're kinda well known",
								"They'll give you their strongest drink and sign your card",
								"When you done all that, we'll be happy to let you in");
						p.getCache().store("barcrawl", true);
					}
				} else if (first == 1) {
					Functions.npcTalk(p, barbarian,
							"Well do I look like a banker to you?");
				}
			} else {
				return;
			}
		}
	}

	@Override
	public void onTalkToNpc(final Player p, final Npc n) {
		if (n.getID() == 305) {
			if (p.getCache().hasKey("barcrawl_completed")) {
				Functions.npcTalk(p, n, "Ello friend");
				return;
			}
			if (p.getCache().hasKey("barcrawl")) {
				Functions.npcTalk(p, n, "So hows the barcrawl coming along?");
				if (!Functions.hasItem(p, 668)) {
					final int third = Functions.showMenu(p, n, new String[] {
							"I've lost my barcrawl card",
							"Not to bad, my barcrawl card is in my bank now" });
					if (third == 0) {
						Functions.npcTalk(p, n, "What are you like?",
								"You're gonna have to start all over now",
								"Here you go, have another barcrawl card");
						Functions.addItem(p, 668, 1);
						p.getCache().remove("barone");
						p.getCache().remove("bartwo");
						p.getCache().remove("barthree");
						p.getCache().remove("barfour");
						p.getCache().remove("barfive");
						p.getCache().remove("barsix");
					} else if (third == 1) {
						Functions.npcTalk(p, n,
								"You need it with you when you are going on a barcrawl");
					}
					return;
				} else if (p.getCache().hasKey("barone")
						&& p.getCache().hasKey("bartwo")
						&& p.getCache().hasKey("barthree")
						&& p.getCache().hasKey("barfour")
						&& p.getCache().hasKey("barfive")
						&& p.getCache().hasKey("barsix") && Functions.hasItem(p, 668)) {
					Functions.playerTalk(p, n,
							"I think I jusht about done them all, but I losht count");
					Functions.message(p, "You give the card to the barbarian");
					Functions.removeItem(p, 668, 1);
					Functions.npcTalk(p,
							n,
							"Yep that seems fine",
							"I never learned to read, but you look like you've drunk plenty",
							"You can come in now");
					p.getCache().store("barcrawl_completed", true);
				} else {
					Functions.playerTalk(p, n, "I haven't finished it yet");
					Functions.npcTalk(p, n,
							"Well come back when you have, you lightweight");
				}
				return;
			}
			Functions.npcTalk(p, n, "Oi whaddya want?");
			final int first = Functions.showMenu(p, n, new String[] {
					"I want to come through this gate", "I want some money" });
			if (first == 0) {
				Functions.npcTalk(p, n, "Barbarian only", "Are you a barbarian?",
						"You don't look like one");
				final int second = Functions.showMenu(p, n, new String[] {
						"Hmm, yep you've got me there",
						"Looks can be deceiving, I am in fact a barbarian" });
				if (second == 0) {
					// NOTHING
				} else if (second == 1) {
					Functions.npcTalk(p,
							n,
							"If you're a barbarian you need to be able to drink like one",
							"We barbarians like a good drink",
							"And I have the perfect challenge for you",
							"The Alfred Grimhand barcrawl",
							"First done by Alfred Grimhand");
					Functions.message(p, "The guard hands you a barcrawl card");
					Functions.addItem(p, 668, 1);
					Functions.npcTalk(p,
							n,
							"Take that card to each of the bars named on it",
							"The bartenders all know what it means",
							"We're kinda well known",
							"They'll give you their strongest drink and sign your card",
							"When you done all that, we'll be happy to let you in");
					p.getCache().store("barcrawl", true);
				}
			} else if (first == 1) {
				Functions.npcTalk(p, n, "Well do I look like a banker to you?");
			}
		}
	}
}
