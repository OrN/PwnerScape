package com.pwns.server.plugins.npcs.varrock;

import static com.pwns.server.plugins.Functions.npcTalk;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class HeadChef implements TalkToNpcExecutiveListener, TalkToNpcListener {

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		Functions.npcTalk(p, n, "Hello welcome to the chef's guild",
				"Only accomplished chefs and cooks are allowed in here",
				"Feel free to use any of our facilities");
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 133;
	}

}
