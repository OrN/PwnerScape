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
import com.pwns.server.plugins.Functions;

public class King implements TalkToNpcListener, TalkToNpcExecutiveListener {

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 42;
	}

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		if (Functions.hasItem(p, 53) && Functions.hasItem(p, 54)) {
			Functions.playerTalk(p, n, "Your majesty",
					"I have recovered the shield of Arrav",
					"I would like to claim the reward");
			Functions.npcTalk(p, n, "The shield of Arrav, eh?",
					"Yes, I do recall my father putting a reward out for that",
					"Very well",
					"Go get the authenticity of the shield verified",
					"By the curator at the museum",
					"And I will grant you your reward");
			return;// Can u test it from beginning to end? sure thing btw, need
					// todo the door.. :/ which one
		} else if (Functions.hasItem(p, 61)
				&& p.getQuestStage(Constants.Quests.SHIELD_OF_ARRAV) == 5) {
			Functions.playerTalk(p, n, "Your majesty", "I have come to claim the reward",
					"For the return of the shield of Arrav");
			Functions.message(p, "You show the certificate to the king");
			Functions.npcTalk(p, n, "My goodness",
					"This is the claim for a reward put out by my father",
					"I never thought I'd see anyone claim this reward",
					"I see you are claiming half the reward",
					"So that would come to 600 gold coins");
			Functions.message(p, "You hand the certificate",
					"The king gives you 600 coins");
			Functions.removeItem(p, 61, 1);
			p.sendQuestComplete(Constants.Quests.SHIELD_OF_ARRAV);
			return;
		}
		Functions.playerTalk(p, n, "Greetings your majesty");
		Functions.npcTalk(p, n, "Do you have anything of import to say?");
		Functions.playerTalk(p, n, "Not really");
		Functions.npcTalk(p, n, "You will have to excuse me then", "I am very busy",
				"I have a kingdom to run");
	}
}
