package com.pwns.server.plugins.npcs.ardougne.east;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class BartenderFlyingHorseInn implements TalkToNpcListener, TalkToNpcExecutiveListener {

	public final int BARTENDER = 340;

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == BARTENDER) {
			return true;
		}
		return false;
	}

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		if(n.getID() == BARTENDER) {
			Functions.npcTalk(p, n, "Would you like to buy a drink?");
			Functions.playerTalk(p, n, "What do you serve?");
			Functions.npcTalk(p, n, "Beer");
			int menu = Functions.showMenu(p, n,
					"I'll have a beer then",
					"I'll not have anything then");
			if(menu == 0) {
				Functions.npcTalk(p, n, "Ok, that'll be two coins");
				if(Functions.hasItem(p, 10, 2)) {
					Functions.removeItem(p, 10, 2);
					Functions.addItem(p, 193, 1);
					p.message("You buy a pint of beer");
				} else {
					Functions.playerTalk(p, n, "Oh dear. I don't seem to have enough money");
				}
			}
		}
	}
}
