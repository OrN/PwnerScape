package com.pwns.server.plugins.npcs.entrana;

import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.Constants;
import com.pwns.server.plugins.Functions;

public class HighPriestOfEntrana implements TalkToNpcExecutiveListener, TalkToNpcListener {

	class EntranaPriest {
		public static final int fisherKing = 0;
		public static final int fourHeads = 1;
		public static final int whistle = 2;
	}

	private static void entranaPriestDialogue(Player p, Npc n, int cID) {
		if(cID == -1) {
			Functions.npcTalk(p,n, "Many greetings welcome to our fair island");
			if(p.getQuestStage(Constants.Quests.THE_HOLY_GRAIL) >= 2) {
				Functions.playerTalk(p,n, "Hello, I am in search of the holy grail");
				Functions.npcTalk(p,n, "The object of which you speak did once pass through holy entrana",
						"I know not where it is now",
						"Nor do I really care");
				n = Functions.getNearestNpc(p, 394, 20);
				Functions.npcTalk(p, n, "Wait!",
						"Did you say the grail?",
						"You are a grail knight yes?",
						"Well you'd better hurry, a fisher king is in pain");
				Functions.playerTalk(p, n, "Well I would but I don't know where I am going");
				Functions.npcTalk(p,n, "Go to where the six heads face",
						"blow the whistle and away you go");
				if(p.getQuestStage(Constants.Quests.THE_HOLY_GRAIL) == 2) {
					p.updateQuestStage(Constants.Quests.THE_HOLY_GRAIL, 3);
				}
				int menu = Functions.showMenu(p, n,
						"What are the six heads?",
						"What's a fisher king?",
						"Ok I will go searching",
						"What do you mean by the whistle?");
				if(menu == 0) {
					entranaPriestDialogue(p, n, EntranaPriest.fourHeads);
				} else if(menu == 1) {
					entranaPriestDialogue(p, n, EntranaPriest.fisherKing);
				} else if(menu == 3) {
					entranaPriestDialogue(p, n, EntranaPriest.whistle);
				}
				return;
			} else {
				Functions.npcTalk(p,n, "enjoy your stay hear",
						"May it be spiritually uplifting");
			}
		}
		switch (cID) {
		case EntranaPriest.fisherKing:
			Functions.npcTalk(p,n, "The fisher king is the owner and slave of the grail");
			Functions.playerTalk(p,n, "What are the four heads?");
			entranaPriestDialogue(p, n, EntranaPriest.fourHeads);
			break;
		case EntranaPriest.fourHeads:
			Functions.npcTalk(p,n, "The six  stone heads have appeared just recently in the world",
					"They all face the point of realm crossing",
					"Find where two of the heads face",
					"And you should be able to pinpoint where it is");
			int m = Functions.showMenu(p,n,
					"What's a fisher king?",
					"Ok I will go searching",
					"What do you mean by the whistle?",
					"the point of realm crossing?");
			if(m == 0) {
				entranaPriestDialogue(p, n, EntranaPriest.fisherKing);
			} else if(m == 2) {
				entranaPriestDialogue(p, n, EntranaPriest.whistle);
			} else if(m == 3) {
				Functions.npcTalk(p, n, "The realm of the fisher king is not quite of this reality",
						"It is of a reality very close to ours though",
						"Where it's easiest to cross that is a point of realm crossing",
						"Many greetings welcome to our fair island");
			}
			break;
		case EntranaPriest.whistle:
			Functions.npcTalk(p,n, "You don't know about the whistles yet?",
					"the whistles are easy",
					"You will need one to get to and from the fisher king's realm",
					"they reside in a haunted manor house in Misthalin",
					"though you may not perceive them unless you carry something",
					"From the realm of the fisher king");
			int m1 = Functions.showMenu(p,n,
					"What are the four heads?",
					"What's a fisher king?",
					"Ok I will go searching");
			if(m1 == 0) {
				entranaPriestDialogue(p, n, EntranaPriest.fourHeads);
			} else if(m1 == 1) {
				entranaPriestDialogue(p, n, EntranaPriest.fisherKing);
			}
			break;
		}
	}
	@Override
	public void onTalkToNpc(final Player p, final Npc n) {
		if(n.getID() == 395) {
			entranaPriestDialogue(p, n, -1);
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 395;
	}

}