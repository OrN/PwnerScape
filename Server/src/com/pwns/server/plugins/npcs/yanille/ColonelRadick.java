package com.pwns.server.plugins.npcs.yanille;

import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class ColonelRadick implements TalkToNpcListener, TalkToNpcExecutiveListener {

	public static int COLONEL_RADICK = 518;

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == COLONEL_RADICK;
	}

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		if(n.getID() == COLONEL_RADICK) {
			Functions.npcTalk(p,n, "Who goes there?",
					"friend or foe?");
			int menu = Functions.showMenu(p,n,
					"Friend",
					"foe",
					"Why's this town so heavily defended?");
			if(menu == 0) {
				Functions.npcTalk(p,n, "Ok good to hear it");
			} else if(menu == 1) {
				Functions.npcTalk(p,n, "Oh righty");
				n.startCombat(p);
			} else if(menu == 2) {
				Functions.npcTalk(p,n, "Yanille is on the southwest border of Kandarin",
						"Beyond here you go into the feldip hills",
						"Which is major ogre teritory",
						"Our job is to defend Yanille from the ogres");
			}
		}
	}
}