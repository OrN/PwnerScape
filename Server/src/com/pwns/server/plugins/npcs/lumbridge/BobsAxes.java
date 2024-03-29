package com.pwns.server.plugins.npcs.lumbridge;

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

public final class BobsAxes implements ShopInterface,
		TalkToNpcExecutiveListener, TalkToNpcListener {

	private final Shop shop = new Shop(false, 15000, 100, 60, 2, new Item(156,
			5), new Item(87, 10), new Item(12, 5), new Item(88, 3),
			new Item(89, 5), new Item(90, 2), new Item(91, 1));

	@Override
	public boolean blockTalkToNpc(final Player p, final Npc n) {
		return n.getID() == 1;
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
		npcTalk(p, n, "Hello. How can I help you?");
		int option = showMenu(p, n, "Give me a quest!",
				"Have you anything to sell?");
		switch (option) {
		case 0:
			npcTalk(p, n, "Get yer own!");
			break;
		case 1:
			npcTalk(p, n, "Yes, I buy and sell axes, take your pick! (or axe)");
			p.setAccessingShop(shop);
			ActionSender.showShop(p, shop);
			break;
		}
	}
}
