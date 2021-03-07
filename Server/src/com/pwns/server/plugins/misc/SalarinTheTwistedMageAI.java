package com.pwns.server.plugins.misc;

import static com.pwns.server.plugins.Functions.sleep;

import com.pwns.server.model.Skills;
import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.model.entity.update.ChatMessage;
import com.pwns.server.plugins.listeners.action.PlayerMageNpcListener;
import com.pwns.server.plugins.listeners.executive.PlayerMageNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class SalarinTheTwistedMageAI implements PlayerMageNpcListener, PlayerMageNpcExecutiveListener {
	
	/*
	 * Player maging Salarin the twisted AI - Just to degenerate ATTACK AND STRENGTH if over 2 in said skill.
	 */

	@Override
	public boolean blockPlayerMageNpc(Player p, Npc n) {
		if(n.getID() == 567 && (p.getSkills().getLevel(Skills.ATTACK) > 2 || p.getSkills().getLevel(Skills.STRENGTH) > 2)) {
			return true;
		}
		return false;
	}

	@Override
	public void onPlayerMageNpc(Player p, Npc n) {
		if(n.getID() == 567 && (p.getSkills().getLevel(Skills.ATTACK) > 2 || p.getSkills().getLevel(Skills.STRENGTH) > 2)) {
			if(!p.withinRange(n, 5)) 
				return;
			n.getUpdateFlags().setChatMessage(new ChatMessage(n, "Amshalaraz Nithcosh dimarilo", p));
			Functions.sleep(600);
			p.message("You suddenly feel much weaker");
			p.getSkills().setLevel(Skills.ATTACK, 0);
			p.getSkills().setLevel(Skills.STRENGTH, 0);
		}
	}
}
