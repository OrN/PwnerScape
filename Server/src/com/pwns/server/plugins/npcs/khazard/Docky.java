package com.pwns.server.plugins.npcs.khazard;

import static com.pwns.server.plugins.Functions.hasItem;
import static com.pwns.server.plugins.Functions.message;
import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.removeItem;
import static com.pwns.server.plugins.Functions.showMenu;
import static com.pwns.server.plugins.Functions.sleep;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class Docky implements TalkToNpcExecutiveListener, TalkToNpcListener {

	public static final int DOCKY = 390;

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		if(n.getID() == DOCKY) {
			Functions.playerTalk(p, n, "hello there");
			Functions.npcTalk(p, n, "ah hoy there, wanting",
					"to travel on the beatiful",
					"lady valentine are we");
			int menu = Functions.showMenu(p, n, "not really, just looking around", "where are you travelling to");
			if(menu == 0) {
				Functions.npcTalk(p, n, "o.k land lover");
			} else if(menu == 1) {
				Functions.npcTalk(p, n, "we sail direct to Birmhaven port",
						"it really is a speedy crossing",
						"so would you like to come",
						"it cost's 30 gold coin's");
				int travel = Functions.showMenu(p, n, "no thankyou", "ok");
				if(travel == 1) {
					if(Functions.hasItem(p, 10, 30)) {
						Functions.message(p, 1900, "You pay 30 gold");
						Functions.removeItem(p, 10, 30);
						Functions.message(p, 3000, "You board the ship");
						p.teleport(467, 647);
						Functions.sleep(2000);
						p.message("The ship arrives at Port Birmhaven");
					} else {
						Functions.playerTalk(p,n, "Oh dear I don't seem to have enough money");
					}
				}
			}
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == DOCKY;
	}
}
