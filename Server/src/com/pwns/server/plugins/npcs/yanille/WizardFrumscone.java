package com.pwns.server.plugins.npcs.yanille;

import static com.pwns.server.plugins.Functions.npcTalk;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class WizardFrumscone implements TalkToNpcListener, TalkToNpcExecutiveListener {
	
	public static int WIZARD_FRUMSCONE = 515;

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == WIZARD_FRUMSCONE;
	}

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		if(n.getID() == WIZARD_FRUMSCONE) {
			Functions.npcTalk(p,n, "Do you like my magic zombies",
					"Feel free to kill them",
					"Theres plenty more where these came from");
		}
	}
}
