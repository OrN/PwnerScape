package com.pwns.server.plugins.npcs.edgeville;

import static com.pwns.server.plugins.Functions.addItem;
import static com.pwns.server.plugins.Functions.message;
import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.removeItem;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;

public class BrotherJered implements TalkToNpcExecutiveListener,
TalkToNpcListener {

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		npcTalk(p, n,
				"Hello friend, would you like me to bless your amulet of saradomin?");
		int option = showMenu(p, n, "Yes please", "No thanks");
		if (option == 0) {
			if (removeItem(p, 45, 1)) {
				message(p, "He quickly takes your amulet",
						"He hands you back a blessed amulet of saradomin");
				addItem(p, 385, 1);
			} else {
				npcTalk(p, n, "Oh dear looks like you don't have any amulet to bless");
			}
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 176;
	}

}
