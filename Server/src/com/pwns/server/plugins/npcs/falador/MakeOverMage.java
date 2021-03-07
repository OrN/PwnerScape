package com.pwns.server.plugins.npcs.falador;

import static com.pwns.server.plugins.Functions.hasItem;
import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.removeItem;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.model.world.World;
import com.pwns.server.net.rsc.ActionSender;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class MakeOverMage implements TalkToNpcListener,
        TalkToNpcExecutiveListener {
	/**
	 * World instance
	 */
	public World world = World.getWorld();

	@Override
	public void onTalkToNpc(Player p, final Npc n) {
		Functions.npcTalk(p, n, "Are you happy with your looks?",
				"If not I can change them for the cheap cheap price",
				"Of 3000 coins");
		int opt = Functions.showMenu(p, n, "I'm happy with how I look thank you",
				"Yes change my looks please");
		if (opt == 1) {
			if (!Functions.hasItem(p, 10, 3000)) {
				Functions.playerTalk(p, n,"I'll just go get the cash");
			} else {
				Functions.removeItem(p, 10, 3000);
				p.setChangingAppearance(true);
				ActionSender.sendAppearanceScreen(p);
			}
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 339;
	}

}
