package com.pwns.server.plugins.npcs;

import static com.pwns.server.plugins.Functions.message;
import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class MonkHealer implements TalkToNpcListener, TalkToNpcExecutiveListener {
	@Override
	public void onTalkToNpc(Player p, final Npc n) {
		Functions.npcTalk(p, n, "Greetings traveller");
		int option = Functions.showMenu(p,n, "Can you heal me? I'm injured", "Greetings");
		if(option == 0) {
			Functions.npcTalk(p,n, "Ok");
			Functions.message(p, "The monk places his hands on your head", "You feel a little better");
			int newHp = p.getSkills().getLevel(3) + 10;
			if (newHp > p.getSkills().getMaxStat(3)) {
				newHp = p.getSkills().getMaxStat(3);
			}
			p.getSkills().setLevel(3, newHp);
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 93 || n.getID() == 174;
	}
}
