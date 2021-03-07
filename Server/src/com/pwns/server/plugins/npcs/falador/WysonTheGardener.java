package com.pwns.server.plugins.npcs.falador;

import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.removeItem;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class WysonTheGardener implements TalkToNpcListener, TalkToNpcExecutiveListener {

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 116;
	}

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		Functions.npcTalk(p,n, "i am the gardener round here",
                          "do you have any gardening that needs doing?");
		int option = Functions.showMenu(p,n, "I'm looking for woad leaves", "Not right now thanks");
		if(option == 0) {
			Functions.npcTalk(p,n, "well luckily for you i may have some around here somewhere");
			Functions.playerTalk(p,n, "can i buy one please?");
			Functions.npcTalk(p,n, "how much are you willing to pay?");
			int sub_option = Functions.showMenu(p,n, "How about 5 coins?", "How about 10 coins?",
                                   "How about 15 coins?", "How about 20 coins?");
			if(sub_option == 2) {
				Functions.npcTalk(p,n, "mmmm ok that sounds fair.");
				if(Functions.removeItem(p, 10, 15)) {
					Functions.addItem(p, 281, 1);
					p.message("you give wyson 15 coins");
					p.message("wyson the gardener gives you some woad leaves");
				} else 
					Functions.playerTalk(p,n, "i dont have enough coins to buy the leaves. i'll come back later");
			}
			else if(sub_option == 3) {
				Functions.npcTalk(p,n, "i used to have plenty but someone kept stealing them off me");
				if(Functions.removeItem(p, 10, 20)) {
					Functions.addItem(p, 281, 2);
					p.message("you give wyson 20 coins");
					p.message("wyson the gardener gives you some woad leaves");
				} else 
					Functions.playerTalk(p,n, "i dont have enough coins to buy the leaves. i'll come back later");
			}
		}
	}

}
