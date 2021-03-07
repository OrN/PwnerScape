package com.pwns.server.plugins.npcs.yanille;

import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class SigbertTheAdventurer implements TalkToNpcListener, TalkToNpcExecutiveListener {

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 573;
	}

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		if(n.getID() == 573) {
			Functions.npcTalk(p,n, "I'd be very careful going up there friend");
			int menu = Functions.showMenu(p,n,
			"Why what's up there?",
			"Fear not I am very strong");
			if(menu == 0) {
				Functions.npcTalk(p,n, "Salarin the twisted",
						"One of Kanadarin's most dangerous chaos druids",
						"I tried to take him on and then suddenly felt immensly week",
						"I here he's susceptable to attacks from the mind",
						"However I have no idea what that means",
						"So it's not much help to me");
			} else if(menu == 1) {
				Functions.npcTalk(p,n, "You might find you are not so strong shortly");
			}
		}
	}
}
