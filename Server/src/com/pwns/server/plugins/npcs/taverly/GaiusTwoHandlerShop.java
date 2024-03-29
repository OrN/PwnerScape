package com.pwns.server.plugins.npcs.taverly;

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

public class GaiusTwoHandlerShop  implements ShopInterface,
        TalkToNpcListener, TalkToNpcExecutiveListener {

	private final int GAIUS = 228;
	private final Shop shop = new Shop(false, 30000, 100, 60, 2,
			new Item(76, 4), new Item(77, 3), new Item(78, 2),
			new Item(426, 1), new Item(79, 1), new Item(80, 1));

	@Override
	public boolean blockTalkToNpc(final Player p, final Npc n) {
		return n.getID() == GAIUS;
	}

	@Override
	public Shop[] getShops() {
		return new Shop[] { shop };
	}

	@Override
	public boolean isMembers() {
		return true;
	}

	@Override
	public void onTalkToNpc(final Player p, final Npc n) {
		Functions.npcTalk(p, n, "Welcome to my two handed sword shop");
		final int option = Functions.showMenu(p, n, new String[] { "Let's trade",
				"Thankyou" });
		if (option == 0) {
			p.setAccessingShop(shop);
			ActionSender.showShop(p, shop);
		}
	}

}
