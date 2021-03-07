package com.pwns.server.plugins.npcs.ardougne.east;

import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class Gunnjorn implements TalkToNpcListener, TalkToNpcExecutiveListener {

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if (n.getID() == 588) {
			return true;
		}
		return false;
	}

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		if (n.getID() == 588) {
			Functions.npcTalk(p, n, "Ahoy there!");
			int menu = Functions.showMenu(p, n, "What is this place?");
			if(menu == 0) {
				Functions.npcTalk(p, n, "Haha welcome to my obstacle course",
						"Have fun, but remember this isn't a child's playground",
						"People have died here", "The best way to train",
						"Is to go round the course in a clockwise direction");
			}
		}
	}
}
