package com.pwns.server.plugins.npcs.portsarim;

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

public final class BriansBattleAxes implements ShopInterface,
		TalkToNpcExecutiveListener, TalkToNpcListener {

	private final Shop shop = new Shop(false, 15000, 100, 55, 1, new Item(205,
			4), new Item(89, 3), new Item(90, 2), new Item(429, 1),
			new Item(91, 1), new Item(92, 1));

	@Override
	public boolean blockTalkToNpc(final Player p, final Npc n) {
		return n.getID() == 131;
	}

	@Override
	public Shop[] getShops() {
		return new Shop[] { shop };
	}

	@Override
	public boolean isMembers() {
		return false;
	}

	@Override
	public void onTalkToNpc(final Player p, final Npc n) {
		npcTalk(p, n, "ello");
		int option = showMenu(p, n, "So are you selling something", "ello");
		switch (option) {
		case 0:
			npcTalk(p, n, "Yep take a look at these great axes");
			p.setAccessingShop(shop);
			ActionSender.showShop(p, shop);
			break;
		}
	}

}
