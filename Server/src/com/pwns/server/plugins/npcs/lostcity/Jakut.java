package com.pwns.server.plugins.npcs.lostcity;

import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.Shop;
import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.net.rsc.ActionSender;
import com.pwns.server.plugins.ShopInterface;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;

public final class Jakut implements ShopInterface, TalkToNpcExecutiveListener,
		TalkToNpcListener {

	private final Shop shop = new Shop(false, 3000, 100, 60,2,
			new Item(593, 2));

	@Override
	public void onTalkToNpc(Player p, final Npc n) {
		npcTalk(p, n, "Dragon swords, get your Dragon swords",
				"Straight from the plane of frenaskrae");

		int option = showMenu(p, n, "Yes please",
				"No thank you, I'm just browsing the marketplace");
		switch (option) {

		case 0:
			p.setAccessingShop(shop);
			ActionSender.showShop(p, shop);
			break;
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 220;
	}

	@Override
	public Shop[] getShops() {
		return new Shop[] { shop };
	}

	@Override
	public boolean isMembers() {
		return true;
	}

}
