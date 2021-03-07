package com.pwns.server.plugins.npcs.alkharid;

import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.removeItem;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public final class KebabSeller implements TalkToNpcListener,
        TalkToNpcExecutiveListener {

	@Override
	public void onTalkToNpc(Player p, final Npc n) {
		Functions.npcTalk(p, n, "Would you like to buy a nice kebab? Only 1 gold");
		int o = Functions.showMenu(p, n, "I think I'll give it a miss", "Yes please");
		if (o == 1) {
			if (Functions.removeItem(p, 10, 1)) {
				p.message("You buy a kebab");
				Functions.addItem(p, 210, 1);
			} else {
				Functions.playerTalk(p, n, "Oops I forgot to bring any money with me");
				Functions.npcTalk(p, n, "Come back when you have some");
			}
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 90;
	}

}
