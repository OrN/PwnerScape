package com.pwns.server.plugins.npcs.alkharid;

import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class SilkTrader implements TalkToNpcListener,
        TalkToNpcExecutiveListener {

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 71;
	}

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		Functions.npcTalk(p, n, "Do you want to buy any fine silks?");
		int option = Functions.showMenu(p, n, "How much are they?",
				"No. Silk doesn't suit me");
		if (option == 0) {
			Functions.npcTalk(p, n, "3 Coins");
			int sub_opt = Functions.showMenu(p, n, "No. That's too much for me",
					"OK, that sounds good");
			if (sub_opt == 0) {
			} else if (sub_opt == 1) {
				if (p.getInventory().remove(10, 3) > -1) {
					Functions.addItem(p, 200, 1);
					p.message("You buy some silk for 3 coins");
				} else {
					Functions.playerTalk(p, n, "Oh dear. I don't have enough money");
				}
			}
		}
	}
}
