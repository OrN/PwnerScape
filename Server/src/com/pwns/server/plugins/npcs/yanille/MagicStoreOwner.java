package com.pwns.server.plugins.npcs.yanille;

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
import com.pwns.server.plugins.Functions;

public final class MagicStoreOwner implements ShopInterface,
        TalkToNpcExecutiveListener, TalkToNpcListener {

	private final Shop shop = new Shop(false, 3000, 100, 75, 2,
			new Item(31, 500), new Item(32, 500), new Item(33, 500),
			new Item(34, 500), new Item(35, 500), new Item(36, 500),
			new Item(825, 30), new Item(614, 5), new Item(101, 2),
			new Item(102, 2), new Item(103, 2), new Item(197, 2));

	@Override
	public void onTalkToNpc(Player p, final Npc n) {
		Functions.npcTalk(p, n, "Welcome to the magic guild store",
				"would you like to buy some magic supplies");

		int option = Functions.showMenu(p, n, "Yes please", "No thank you");
		switch (option) {
		case 0:
			p.setAccessingShop(shop);
			ActionSender.showShop(p, shop);
			break;
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 514;
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
